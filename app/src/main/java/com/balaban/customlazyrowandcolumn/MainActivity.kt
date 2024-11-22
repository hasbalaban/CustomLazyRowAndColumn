package com.balaban.customlazyrowandcolumn

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeAddItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeQuoteTweetItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeSplitScreenItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeTweetItem
import com.balaban.customlazyrowandcolumn.models.AddScreenItem
import com.balaban.customlazyrowandcolumn.models.ContentTypes
import com.balaban.customlazyrowandcolumn.models.ItemType
import com.balaban.customlazyrowandcolumn.models.QuoteTweetScreenItem
import com.balaban.customlazyrowandcolumn.models.SplitScreenItem
import com.balaban.customlazyrowandcolumn.models.TweetScreenItem
import com.balaban.customlazyrowandcolumn.models.getItems
import com.balaban.customlazyrowandcolumn.ui.theme.CustomLazyRowAndColumnTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomLazyRowAndColumnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val list by remember {
        mutableStateOf(getItems().shuffled().shuffled())
    }


    var confirmText by remember { mutableStateOf("") }

    val properties by remember {
        mutableStateOf(
            DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                securePolicy = SecureFlagPolicy.SecureOn,
            )
        )
    }

    if (confirmText.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = {
                confirmText = ""
            },
            confirmButton = {
                Text(
                    modifier = Modifier.clickable {
                        confirmText = ""
                    },
                    text = "confirmText")
            },
            dismissButton = {
                Text(
                    modifier = Modifier.clickable {
                        confirmText = ""
                    },
                    text = "dismissText")
            },
            text = {
                Text(
                    modifier = Modifier.clickable {
                        confirmText = ""
                    },
                    text = confirmText)
            },
            properties = properties
        )

    }

    Column(modifier = modifier) {
        ListWithDifferentContentTypes(
            list = list,
            setOnListeners = {clickListenerType ->
                when (clickListenerType) {
                    is ClickListeners.ItemClickListener -> {
                        confirmText = when(clickListenerType.item){
                            is AddScreenItem -> clickListenerType.item.type.toString().replace("_", " ")
                            is SplitScreenItem -> clickListenerType.item.type.toString().replace("_", " ")
                            is TweetScreenItem -> clickListenerType.item.type.toString().replace("_", " ")
                            is QuoteTweetScreenItem -> clickListenerType.item.type.toString().replace("_", " ")
                            else -> ""
                        }
                    }

                    is ClickListeners.ItemLongClickListener -> {
                        confirmText = ""
                        when(clickListenerType.item){
                            is AddScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " ") + " ItemLongClickListener")
                            is SplitScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " ") + " ItemLongClickListener")
                            is TweetScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " ") + " ItemLongClickListener")
                            is QuoteTweetScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " ") + " ItemLongClickListener")
                        }
                    }
                }
            }
        )
    }
}


private fun homeClickListeners(clickListenerType: ClickListeners, context: Context) {
}

private fun showToastMessage(context: Context, text: String) =
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()


sealed class ClickListeners() {
    data class ItemClickListener(val item: ItemType) : ClickListeners()
    data class ItemLongClickListener(val item: ItemType) : ClickListeners()
}

@Composable
fun ListWithDifferentContentTypes(
    list : List<ItemType>,
    setOnListeners:(ClickListeners) -> Unit
) {

    LazyColumn{
        itemsIndexed(
            items = list,
            key = {_, item ->
                item.id
            },
            contentType = { _, item -> item.type },
        ) { index, item ->

            ScreenHolder(
                item = item,
                setOnListeners = setOnListeners,
            )

            if (index < list.size - 1) {
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp), color = Color.Gray.copy(alpha = 0.2f), thickness = 1.dp)
            }
        }
    }
}


@Composable
private fun ScreenHolder(item: ItemType, setOnListeners: (ClickListeners) -> Unit) {
    when (item.type) {

        ContentTypes.TYPE_ADD_ITEM -> {
            Box(modifier = Modifier.padding(start = 12.dp, end = 6.dp)) {
                LazyColumnTypeAddItem(
                    item = item as AddScreenItem,
                    setOnListeners = setOnListeners
                )
            }
        }

        ContentTypes.TYPE_SPLITTED_SCREEN -> {
            Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                LazyColumnTypeSplitScreenItem(
                    item = item as SplitScreenItem,
                    isQuoteItem = false,
                    setOnListeners = setOnListeners,
                )
            }
        }

        ContentTypes.TYPE_TWEET_SCREEN -> {
            Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                LazyColumnTypeTweetItem(
                    item = item as TweetScreenItem,
                    setOnListeners = setOnListeners,
                )
            }
        }
        ContentTypes.TYPE_QUOTE_TWEET_SCREEN -> {
            Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                LazyColumnTypeQuoteTweetItem(
                    item = item as QuoteTweetScreenItem,
                    setOnListeners = setOnListeners
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val item = AddScreenItem(image = R.drawable.image, id = 0)
    CustomLazyRowAndColumnTheme {
        Column(modifier = Modifier.padding(12.dp)){
            LazyColumnTypeAddItem(item){

            }
        }
    }
}
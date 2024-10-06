package com.balaban.customlazyrowandcolumn

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeAddItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeQuoteTweetItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeSplitScreenItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeTweetItem
import com.balaban.customlazyrowandcolumn.models.AddScreenItem
import com.balaban.customlazyrowandcolumn.models.ItemType
import com.balaban.customlazyrowandcolumn.models.QuoteTweetScreenItem
import com.balaban.customlazyrowandcolumn.models.SplitScreenItem
import com.balaban.customlazyrowandcolumn.models.TweetScreenItem
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
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val list by remember {
        mutableStateOf(items.shuffled().shuffled())
    }

    Column(modifier = modifier) {
        ListWithDifferentContentTypes(
            list = list,
            setOnListeners = {
            homeClickListeners(it, context = context)
            }
        )
    }
}

private fun homeClickListeners(clickListenerType: ClickListeners, context: Context) {
    when (clickListenerType) {
        is ClickListeners.ItemClickListener -> {
            when(clickListenerType.item){
                is AddScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
                is SplitScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
                is TweetScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
                is QuoteTweetScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
            }
        }

        is ClickListeners.ItemLongClickListener -> {
            when(clickListenerType.item){
                is AddScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
                is SplitScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
                is TweetScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
                is QuoteTweetScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
            }
        }

        is ClickListeners.ItemClickListenerWithParams -> {
            when(clickListenerType.item){
                is AddScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
                is SplitScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
                is TweetScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
                is QuoteTweetScreenItem -> showToastMessage(context = context, text = clickListenerType.item.type.toString().replace("_", " "))
            }
        }
    }
}

private fun showToastMessage(context: Context, text : String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val item = AddScreenItem(image = R.drawable.image)
    CustomLazyRowAndColumnTheme {
        Column(modifier = Modifier.padding(12.dp)){
            LazyColumnTypeAddItem(item){

            }
        }
    }
}


sealed class ClickListeners() {
    data class ItemClickListener(val item: ItemType, val position: Int) : ClickListeners()
    data class ItemLongClickListener(val item: ItemType, val position: Int) : ClickListeners()
    data class ItemClickListenerWithParams(val item: ItemType, val position: Int, val obj: Any) :
        ClickListeners()
}


val items = listOf(
    AddScreenItem(image = R.drawable.image),
    AddScreenItem(image = R.drawable.image),
    AddScreenItem(image = R.drawable.image),
    AddScreenItem(image = R.drawable.image),

    SplitScreenItem(imageList = listOf(R.drawable.ben,R.drawable.ben,R.drawable.ben,R.drawable.ben)),
    SplitScreenItem(imageList = listOf(R.drawable.ben,R.drawable.ben,R.drawable.ben)),
    SplitScreenItem(imageList = listOf(R.drawable.ben,R.drawable.ben)),
    SplitScreenItem(imageList = listOf(R.drawable.ben)),

    TweetScreenItem(text = "R.drawable.image"),
    TweetScreenItem(text = "R.drawable.image"),
    TweetScreenItem(text = "R.drawable.image"),

    QuoteTweetScreenItem(imageList = listOf(R.drawable.ben,R.drawable.ben,R.drawable.ben,R.drawable.ben)),
    QuoteTweetScreenItem(imageList = listOf(R.drawable.ben,R.drawable.ben,R.drawable.ben)),
    QuoteTweetScreenItem(imageList = listOf(R.drawable.ben,R.drawable.ben)),
    QuoteTweetScreenItem(imageList = listOf(R.drawable.ben)),
)

@Composable
fun ListWithDifferentContentTypes(
    list : List<ItemType>,
    setOnListeners:(ClickListeners) -> Unit
) {

    LazyColumn{
        itemsIndexed(
            items = list,
            contentType = { _, item -> item.type },
        ) { index, item ->

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

            if (index < list.size - 1) {
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp), color = Color.Gray.copy(alpha = 0.2f), thickness = 1.dp)
            }
        }
    }
}

enum class ContentTypes {
    TYPE_ADD_ITEM,
    TYPE_SPLITTED_SCREEN,
    TYPE_TWEET_SCREEN,
    TYPE_QUOTE_TWEET_SCREEN,
}
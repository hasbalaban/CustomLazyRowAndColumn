package com.balaban.customlazyrowandcolumn

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.balaban.customlazyrowandcolumn.models.AddScreenItem
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
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var confirmText by remember { mutableStateOf("") }



    if (confirmText.isNotEmpty()) {
        MainAlertDialog(
            confirmText = confirmText,
            onCloseDialog = {
                confirmText = ""
            }
        )
    }

    val rememberItemClickListener = remember {
        { item: ItemType ->
            confirmText = when (item) {
                is AddScreenItem -> formatItemType(item)
                is SplitScreenItem -> formatItemType(item)
                is TweetScreenItem -> formatItemType(item)
                is QuoteTweetScreenItem -> formatItemType(item)
                else -> ""
            }
        }
    }

    val rememberItemLongClickListener = remember {
        { item: ItemType ->
            when (item) {
                is AddScreenItem -> showToastMessage(
                    context = context,
                    text = formatItemType(item) + " ItemLongClickListener"
                )
                is SplitScreenItem -> showToastMessage(
                    context = context,
                    text = formatItemType(item) + " ItemLongClickListener"
                )
                is TweetScreenItem -> showToastMessage(
                    context = context,
                    text = formatItemType(item) + " ItemLongClickListener"
                )
                is QuoteTweetScreenItem -> showToastMessage(
                    context = context,
                    text =  formatItemType(item) + " ItemLongClickListener"
                )
            }
        }
    }


    val lazyViewManager = remember {
        LazyViewManager().apply {
            itemClickListener = rememberItemClickListener
            itemLongClickListener = rememberItemLongClickListener
        }
    }

    Column(modifier = modifier) {
        DifferentContentTypes(lazyViewManager = lazyViewManager)
    }
}

@Composable
private fun MainAlertDialog(
    confirmText: String,
    onCloseDialog: () -> Unit,
){

    val properties = remember {
        DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            securePolicy = SecureFlagPolicy.SecureOn,
        )
    }

    AlertDialog(
        onDismissRequest = {
            onCloseDialog.invoke()
        },
        confirmButton = {
            Text(
                text = "confirmText",
                modifier = Modifier.clickable {
                    onCloseDialog.invoke()
                }
            )
        },
        dismissButton = {
            Text(
                text = "dismissText",
                modifier = Modifier.clickable {
                    onCloseDialog.invoke()
                }
            )
        },
        text = {
            Text(
                text = confirmText,
                modifier = Modifier.clickable {
                    onCloseDialog.invoke()
                }
            )
        },
        properties = properties
    )
}

private fun showToastMessage(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

@Composable
fun DifferentContentTypes(lazyViewManager: LazyViewManager) {
    val list = remember { getItems() }

    LazyColumn(modifier = Modifier) {
        itemsIndexed(
            items = list,
            key = { _, item ->
                item.id
            },
            contentType = { _, item -> item.type },
        ) { index, item ->

            lazyViewManager.ScreenHolder(item = item)

            if (index < list.size - 1) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color.Gray.copy(alpha = 0.2f),
                    thickness = 1.dp
                )
            }
        }
    }
}


fun formatItemType(item: ItemType): String {
    return item.type.toString().replace("_", " ")
}
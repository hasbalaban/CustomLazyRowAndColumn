package com.balaban.customlazyrowandcolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeAddItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeBannerItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeHeaderItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeSplitScreenItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeTextItem
import com.balaban.customlazyrowandcolumn.models.AddScreenItem
import com.balaban.customlazyrowandcolumn.models.BannerScreenItem
import com.balaban.customlazyrowandcolumn.models.HeaderItem
import com.balaban.customlazyrowandcolumn.models.SplitScreenItem
import com.balaban.customlazyrowandcolumn.models.TextItem
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
    Column(modifier = modifier){
        ListWithDifferentContentTypes()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val item = AddScreenItem(image = R.drawable.image)
    CustomLazyRowAndColumnTheme {
        Column(modifier = Modifier.padding(12.dp)){
            LazyColumnTypeAddItem(item)
        }
    }
}


abstract class ViewHolder<T>()  {
    var itemClickListener: ((item: ItemType, position: Int) -> Unit)? = null
    var itemClickListenerWithView: ((item: ItemType, view: Int) -> Unit)? = null
    var itemLongClickListener: ((item: ItemType, position: Int) -> Boolean)? = null
    var itemClickListenerWithParams: ((item: ItemType, position: Int, obj:Any) -> Unit)? = null
    abstract fun bind(item: T)
    open fun onAttachAdapter() {}
    open fun onDetachAdapter() {}
}

open class ItemType (val type: ContentTypes)


val items = listOf(
    AddScreenItem(image = R.drawable.image),
    SplitScreenItem(image = R.drawable.image),
    HeaderItem(
        header = "HeaderItem 1",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    ),
    BannerScreenItem(text = "Custom List")
)

@Composable
fun ListWithDifferentContentTypes() {
    val list by remember {
        mutableStateOf((items + items + items + items))
    }
    LazyColumn{
        itemsIndexed(
            items = list.shuffled(),
            contentType = { _, item -> item.type },
        ) { index, item ->

            when (item.type) {
                ContentTypes.TYPE_TEXT -> {
                    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                        LazyColumnTypeTextItem(item = item as TextItem)
                    }
                }

                ContentTypes.TYPE_HEADER -> {
                    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                        LazyColumnTypeHeaderItem(item = item as HeaderItem)
                    }
                }

                ContentTypes.TYPE_ADD_ITEM -> {
                    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                        LazyColumnTypeAddItem(item = item as AddScreenItem)
                    }
                }

                ContentTypes.TYPE_SPLITTED_SCREEN -> {
                    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                        LazyColumnTypeSplitScreenItem(item = item as SplitScreenItem)
                    }
                }

                ContentTypes.TYPE_BANNER_SCREEN -> {
                    LazyColumnTypeBannerItem(item = item as BannerScreenItem)
                }
            }

            if (index < list.size - 1) {
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp), color = Color.Gray.copy(alpha = 0.2f), thickness = 1.dp)
            }
        }
    }
}

enum class ContentTypes {
    TYPE_TEXT,
    TYPE_HEADER,
    TYPE_ADD_ITEM,
    TYPE_SPLITTED_SCREEN,
    TYPE_BANNER_SCREEN
}
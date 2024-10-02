package com.balaban.customlazyrowandcolumn

import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeAddItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeBannerItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeSplitScreenItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeTextItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeTweetItem
import com.balaban.customlazyrowandcolumn.models.AddScreenItem
import com.balaban.customlazyrowandcolumn.models.BannerScreenItem
import com.balaban.customlazyrowandcolumn.models.ItemType
import com.balaban.customlazyrowandcolumn.models.SplitScreenItem
import com.balaban.customlazyrowandcolumn.models.TextItem
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
}


val items = listOf(
    AddScreenItem(image = R.drawable.image),
    AddScreenItem(image = R.drawable.image),
    AddScreenItem(image = R.drawable.image),
    AddScreenItem(image = R.drawable.image),

    SplitScreenItem(image = R.drawable.image),
    SplitScreenItem(image = R.drawable.image),

    TweetScreenItem(text = "R.drawable.image"),
    TweetScreenItem(text = "R.drawable.image"),
    TweetScreenItem(text = "R.drawable.image"),

    BannerScreenItem(text = "Custom List Banner"),
)

@Composable
fun ListWithDifferentContentTypes() {
    val list by remember {
        mutableStateOf(items.shuffled())
    }
    LazyColumn{
        itemsIndexed(
            items = list,
            contentType = { _, item -> item.type },
        ) { index, item ->

            when (item.type) {
                ContentTypes.TYPE_TEXT -> {
                    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                        LazyColumnTypeTextItem(item = item as TextItem)
                    }
                }

                ContentTypes.TYPE_ADD_ITEM -> {
                    Box(modifier = Modifier.padding(start = 12.dp, end = 6.dp)) {
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

                ContentTypes.TYPE_TWEET_SCREEN -> {
                    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                        LazyColumnTypeTweetItem(item = item as TweetScreenItem)
                    }
                }
            }

            val shouldShowBanner =
                list.getOrNull(index + 1)?.type == ContentTypes.TYPE_BANNER_SCREEN || item.type == ContentTypes.TYPE_BANNER_SCREEN

            if (index < list.size - 1 && !shouldShowBanner) {
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp), color = Color.Gray.copy(alpha = 0.2f), thickness = 1.dp)
            }
        }
    }
}

enum class ContentTypes {
    TYPE_TEXT,
    TYPE_ADD_ITEM,
    TYPE_SPLITTED_SCREEN,
    TYPE_BANNER_SCREEN,
    TYPE_TWEET_SCREEN
}
package com.balaban.customlazyrowandcolumn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeAddItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeQuoteTweetItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeSplitScreenItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.LazyColumnTypeTweetItem
import com.balaban.customlazyrowandcolumn.lazycolumnscreens.RecommendedAccountsScreen
import com.balaban.customlazyrowandcolumn.models.AddScreenItem
import com.balaban.customlazyrowandcolumn.models.ContentTypes
import com.balaban.customlazyrowandcolumn.models.ItemType
import com.balaban.customlazyrowandcolumn.models.QuoteTweetScreenItem
import com.balaban.customlazyrowandcolumn.models.RecommendedAccountsItem
import com.balaban.customlazyrowandcolumn.models.SplitScreenItem
import com.balaban.customlazyrowandcolumn.models.TweetScreenItem

@Stable
class LazyViewManager  {
    var itemClickListener : ((item : ItemType) -> Unit)? = null
    var itemLongClickListener : ((item : ItemType) -> Unit)? = null

    private var size = 0

    @Composable
    fun ScreenHolder(item: ItemType) {
        println(size++)
        when (item.type) {
            ContentTypes.TYPE_ADD_ITEM -> {
                Box(modifier = Modifier.padding(start = 12.dp, end = 6.dp)) {
                    LazyColumnTypeAddItem(item = item as AddScreenItem)
                }
            }

            ContentTypes.TYPE_SPLITTED_SCREEN -> {
                Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                    LazyColumnTypeSplitScreenItem(item = item as SplitScreenItem,)
                }
            }

            ContentTypes.TYPE_TWEET_SCREEN -> {
                Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                    LazyColumnTypeTweetItem(item = item as TweetScreenItem)
                }
            }
            ContentTypes.TYPE_QUOTE_TWEET_SCREEN -> {
                Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                    LazyColumnTypeQuoteTweetItem(item = item as QuoteTweetScreenItem)
                }
            }

            ContentTypes.RECOMMENDED_ACCOUNTS -> {
                Box(modifier = Modifier) {
                    RecommendedAccountsScreen(item = item as RecommendedAccountsItem)
                }
            }
        }
    }
}
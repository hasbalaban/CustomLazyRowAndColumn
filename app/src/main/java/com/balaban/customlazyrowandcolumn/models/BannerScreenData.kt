package com.balaban.customlazyrowandcolumn.models

import androidx.annotation.DrawableRes
import com.balaban.customlazyrowandcolumn.ContentTypes

open class ItemType (val type: ContentTypes)

data class AddScreenItem(@DrawableRes val image: Int) : ItemType(ContentTypes.TYPE_ADD_ITEM)
data class SplitScreenItem(@DrawableRes val imageList: List<Int>) : ItemType(ContentTypes.TYPE_SPLITTED_SCREEN)
data class TweetScreenItem(val text: String) : ItemType(ContentTypes.TYPE_TWEET_SCREEN)
data class QuoteTweetScreenItem(val imageList: List<Int>) : ItemType(ContentTypes.TYPE_QUOTE_TWEET_SCREEN)
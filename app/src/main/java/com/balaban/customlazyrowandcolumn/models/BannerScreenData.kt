package com.balaban.customlazyrowandcolumn.models

import androidx.annotation.DrawableRes
import com.balaban.customlazyrowandcolumn.ContentTypes

open class ItemType (val type: ContentTypes)

data class TextItem(val text: String) : ItemType(ContentTypes.TYPE_TEXT)
data class HeaderItem(val header: String, val description : String) : ItemType(ContentTypes.TYPE_HEADER)
data class AddScreenItem(@DrawableRes val image: Int) : ItemType(ContentTypes.TYPE_ADD_ITEM)
data class SplitScreenItem(@DrawableRes val image: Int) : ItemType(ContentTypes.TYPE_SPLITTED_SCREEN)
data class BannerScreenItem(val text: String) : ItemType(ContentTypes.TYPE_BANNER_SCREEN)
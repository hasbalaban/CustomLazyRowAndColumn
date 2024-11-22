package com.balaban.customlazyrowandcolumn.models

import androidx.annotation.DrawableRes
import com.balaban.customlazyrowandcolumn.R



enum class ContentTypes {
    TYPE_ADD_ITEM,
    TYPE_SPLITTED_SCREEN,
    TYPE_TWEET_SCREEN,
    TYPE_QUOTE_TWEET_SCREEN,
}


open class ItemType (val type: ContentTypes, open val id : Int,)

data class AddScreenItem(@DrawableRes val image: Int, override val id : Int) : ItemType(ContentTypes.TYPE_ADD_ITEM, id = id)
data class SplitScreenItem(@DrawableRes val imageList: List<Int>, override val id : Int) : ItemType(ContentTypes.TYPE_SPLITTED_SCREEN, id = id)
data class TweetScreenItem(val text: String, override val id : Int) : ItemType(ContentTypes.TYPE_TWEET_SCREEN, id = id)
data class QuoteTweetScreenItem(val imageList: List<Int>, override val id : Int) : ItemType(ContentTypes.TYPE_QUOTE_TWEET_SCREEN, id = id)

fun getItems(): MutableList<ItemType> {
    val items: MutableList<ItemType> = mutableListOf()

    items.apply {
        var size = items.size
        addAll(
            listOf(
                AddScreenItem(image = R.drawable.moon, id = size++),
                AddScreenItem(image = R.drawable.cities, id = size++),
                AddScreenItem(image = R.drawable.moon, id = size++),
                AddScreenItem(image = R.drawable.cities, id = size++),

                SplitScreenItem(
                    imageList =
                    listOf(R.drawable.ben, R.drawable.ben, R.drawable.ben, R.drawable.ben),
                    id = size++
                ),
                SplitScreenItem(
                    imageList = listOf(R.drawable.ben, R.drawable.ben, R.drawable.ben),
                    id = size++
                ),
                SplitScreenItem(imageList = listOf(R.drawable.ben, R.drawable.ben), id = size++),
                SplitScreenItem(imageList = listOf(R.drawable.ben), id = size++),

                TweetScreenItem(text = "R.drawable.image", id = size++),
                TweetScreenItem(text = "R.drawable.image", id = size++),
                TweetScreenItem(text = "R.drawable.image", id = size++),

                QuoteTweetScreenItem(
                    imageList =
                    listOf(R.drawable.ben, R.drawable.ben, R.drawable.ben, R.drawable.ben),
                    id = size++
                ),
                QuoteTweetScreenItem(
                    imageList =
                    listOf(R.drawable.ben, R.drawable.ben, R.drawable.ben),
                    id = size++
                ),
                QuoteTweetScreenItem(
                    imageList = listOf(R.drawable.ben, R.drawable.ben),
                    id = size++
                ),
                QuoteTweetScreenItem(imageList = listOf(R.drawable.ben), id = ++size),
            )
        )

    }
    return items
}

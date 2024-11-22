package com.balaban.customlazyrowandcolumn.models

import androidx.annotation.DrawableRes
import com.balaban.customlazyrowandcolumn.R


sealed class ClickListeners() {
    data class ItemClickListener(val item: ItemType) : ClickListeners()
    data class ItemLongClickListener(val item: ItemType) : ClickListeners()
}

open class ItemType (val type: ContentTypes, open val id : Int,)

enum class ContentTypes {
    TYPE_ADD_ITEM,
    TYPE_SPLITTED_SCREEN,
    TYPE_TWEET_SCREEN,
    TYPE_QUOTE_TWEET_SCREEN,
    RECOMMENDED_ACCOUNTS
}

data class User(
    val username: String,
    val userHandle: String,
    val profilePictureUrl: String,
    var isFollowing: Boolean
)


// ContentTypes type and id for performance of lazy column and rows
data class AddScreenItem(@DrawableRes val image: Int, override val id : Int) : ItemType(ContentTypes.TYPE_ADD_ITEM, id = id)
data class SplitScreenItem(@DrawableRes val imageList: List<Int>, override val id : Int) : ItemType(ContentTypes.TYPE_SPLITTED_SCREEN, id = id)
data class TweetScreenItem(val text: String, override val id : Int) : ItemType(ContentTypes.TYPE_TWEET_SCREEN, id = id)
data class QuoteTweetScreenItem(val imageList: List<Int>, override val id : Int) : ItemType(ContentTypes.TYPE_QUOTE_TWEET_SCREEN, id = id)
data class RecommendedAccountsItem(val recommendedUsers: List<User>, override val id : Int) : ItemType(ContentTypes.RECOMMENDED_ACCOUNTS, id = id)

fun getItems(): List<ItemType> {
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
                QuoteTweetScreenItem(imageList = listOf(R.drawable.ben), id = size++),

                RecommendedAccountsItem(
                    listOf(
                        User(
                            username = "Ahmet Yılmaz",
                            userHandle = "ahmetyilmaz",
                            profilePictureUrl = "https://www.example.com/profile1.jpg",
                            isFollowing = false
                        ),
                        User(
                            username = "Emine Kızıl",
                            userHandle = "eminekizil",
                            profilePictureUrl = "https://www.example.com/profile2.jpg",
                            isFollowing = true
                        ),
                        User(
                            username = "Selin Çelik",
                            userHandle = "selincelik",
                            profilePictureUrl = "https://www.example.com/profile3.jpg",
                            isFollowing = false
                        )
                    ),
                    id = ++size
                ),
                RecommendedAccountsItem(
                    listOf(
                        User(
                            username = "Ahmet Yılmaz",
                            userHandle = "ahmetyilmaz",
                            profilePictureUrl = "https://www.example.com/profile1.jpg",
                            isFollowing = false
                        ),
                        User(
                            username = "Emine Kızıl",
                            userHandle = "eminekizil",
                            profilePictureUrl = "https://www.example.com/profile2.jpg",
                            isFollowing = true
                        ),
                        User(
                            username = "Selin Çelik",
                            userHandle = "selincelik",
                            profilePictureUrl = "https://www.example.com/profile3.jpg",
                            isFollowing = false
                        )
                    ),
                    id = ++size
                ),

            )
        )

    }
    return items.shuffled().shuffled()
}

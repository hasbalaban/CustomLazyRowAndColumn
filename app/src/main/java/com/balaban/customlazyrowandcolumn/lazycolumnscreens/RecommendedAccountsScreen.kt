package com.balaban.customlazyrowandcolumn.lazycolumnscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.balaban.customlazyrowandcolumn.models.ClickListeners
import com.balaban.customlazyrowandcolumn.models.ItemType
import com.balaban.customlazyrowandcolumn.models.RecommendedAccountsItem
import com.balaban.customlazyrowandcolumn.models.User


@Composable
fun RecommendedAccountsScreen(
    item: RecommendedAccountsItem,
    setOnListeners: (ClickListeners) -> Unit
) {

    Column(modifier = Modifier
        .pointerInput(Unit) {
            detectTapGestures(
                onLongPress = {
                    setOnListeners.invoke(ClickListeners.ItemLongClickListener(item as ItemType))
                },
                onTap = {
                    setOnListeners.invoke(ClickListeners.ItemClickListener(item as ItemType))
                }
            )
        }
        .padding(16.dp)) {
        Text(
            text = "Önerilen Hesaplar",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier
                .height((4 * 48).dp)
        ) {
            items(item.recommendedUsers) { user ->
                RecommendedAccountItem(user)
            }
        }
    }
}

@Composable
fun RecommendedAccountItem(item: User) {
    var user by remember { mutableStateOf(item) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = user.username,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "@${user.userHandle}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        FollowButton(isFollowing = user.isFollowing) { newStatus ->
            user = user.copy(isFollowing = newStatus)
        }
    }
}

@Composable
fun FollowButton(isFollowing: Boolean, onClick: (Boolean) -> Unit) {
    val buttonText = if (isFollowing) "Takip Ediliyor" else "Takip Et"
    val buttonColor = if (isFollowing) Color.Gray else Color.Blue
    Button(
        onClick = { onClick(!isFollowing) },
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Text(text = buttonText, color = Color.White)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRecommendedAccounts() {
    // Örnek Kullanıcı Verisi
    val users = listOf(
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
    )
    RecommendedAccountsScreen(
        item = RecommendedAccountsItem(recommendedUsers = users, 0),
        setOnListeners = { _ -> }
    )

}


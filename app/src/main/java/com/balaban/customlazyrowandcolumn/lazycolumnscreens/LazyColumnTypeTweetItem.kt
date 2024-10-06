package com.balaban.customlazyrowandcolumn.lazycolumnscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.balaban.customlazyrowandcolumn.ClickListeners
import com.balaban.customlazyrowandcolumn.ContentTypes
import com.balaban.customlazyrowandcolumn.models.TweetScreenItem
import com.balaban.customlazyrowandcolumn.scren.VerifiedImage

@Composable
fun LazyColumnTypeTweetItem(item: TweetScreenItem, setOnListeners: (ClickListeners) -> Unit) {
    Column(
        modifier = Modifier
            .clickable {
                setOnListeners.invoke(ClickListeners.ItemClickListener(item, 0))
            }
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                imageVector = Icons.Filled.AccountCircle, // Replace with the logo
                contentDescription = "CNBC-e Logo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "CNBC-e",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )


                    VerifiedImage(modifier = Modifier.size(16.dp))

                    Text(
                        text = "@cnbceofficial · 1h",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Text(
                    text = "ECB/Guindos: Euro Bölgesi'nde toparlanma zaman içinde güçlenecek",
                    color = Color.Black,
                    modifier = Modifier.padding(top = 4.dp)
                )


                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp))
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    Row(modifier = Modifier) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.LibraryBooks,
                            contentDescription = "Image Placeholder",
                            tint = Color.Black.copy(0.5f),
                            modifier = Modifier
                                .padding(24.dp)
                                .size(32.dp)
                        )

                        VerticalDivider(
                            modifier = Modifier.fillMaxHeight(),
                            color = Color.LightGray
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(modifier = Modifier.padding(top = 16.dp)) {
                            Text(
                                text = "ECB/Guindos: Euro Bölgesi'nde toparlanma z...",
                                color = Color.Black
                            )
                            Text(
                                text = "cnbce.com",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }

    }
}

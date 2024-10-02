package com.balaban.customlazyrowandcolumn.lazycolumnscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balaban.customlazyrowandcolumn.R
import com.balaban.customlazyrowandcolumn.models.SplitScreenItem
import com.balaban.customlazyrowandcolumn.scren.VerifiedImage

@Composable
fun LazyColumnTypeSplitScreenItem(item: SplitScreenItem, isQuoteItem: Boolean) {
    Column {

        Row(
            modifier = Modifier.padding(start = if (isQuoteItem) 12.dp else 0.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ben),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Hesen B.",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    VerifiedImage(modifier = Modifier.size(12.dp))


                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "@hesenblbn101 · 2m",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )

                }
                Text(
                    text = "this is my favorite photo...",
                    color = Color.Black
                )
            }

        }





        Column(
            modifier = Modifier
                .padding(top = 12.dp, start = if (isQuoteItem) 0.dp else 48.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
        ) {
            val chunkedList = item.imageList.chunked(2)

            chunkedList.forEachIndexed { index, chunkedItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = if (index == 0) 0.dp else 2.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    chunkedItem.forEachIndexed { index, image ->
                        Image(
                            modifier = Modifier
                                .heightIn(max = 160.dp)
                                .weight(1f)
                                .padding(start = if (index == 0) 0.dp else 2.dp),
                            painter = painterResource(id = image),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }

        }
    }
}

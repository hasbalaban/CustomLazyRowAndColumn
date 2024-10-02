package com.balaban.customlazyrowandcolumn.lazycolumnscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balaban.customlazyrowandcolumn.R
import com.balaban.customlazyrowandcolumn.models.QuoteTweetScreenItem

@Composable
fun LazyColumnTypeQuoteTweetItem(item: QuoteTweetScreenItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Header Row
        Row(verticalAlignment = Alignment.CenterVertically) {
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
                    Image(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Verified",
                        modifier = Modifier.size(16.dp)
                            .clip(CircleShape)
                            .background(Color(0xff1d9bf0)).padding(2.dp),
                        colorFilter = ColorFilter
                            .tint(Color.White)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "@hesenblbn101 · 2m",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

                Text(
                    text = "Let's look this tweet",
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))


        Column(
            modifier = Modifier
                .padding(start = 40.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp))
                    .padding(12.dp)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Hesen B.",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Verified",
                        modifier = Modifier.size(12.dp)
                            .clip(CircleShape)
                            .background(Color(0xff1d9bf0)).padding(2.dp),
                        colorFilter = ColorFilter
                            .tint(Color.White)
                    )

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


            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Prayer Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .background(Color.Blue)
                    .fillMaxWidth()
                    .height(200.dp)
            )

        }
    }
}
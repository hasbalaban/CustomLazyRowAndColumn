package com.balaban.customlazyrowandcolumn.lazycolumnscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balaban.customlazyrowandcolumn.models.SplitScreenItem

@Composable
fun LazyColumnTypeSplitScreenItem(item: SplitScreenItem) {
    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Filled.AccountCircle, // Replace with the logo
                contentDescription = "CNBC-e Logo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))



            Text(
                modifier = Modifier,
                text = "I Shared my favorite photos",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 12.dp, start = 40.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier.padding(end = 1.dp).weight(1f),
                    painter = painterResource(id = item.image),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .padding(start = 1.dp)
                        .weight(1f),
                    painter = painterResource(id = item.image),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
            ) {
                Image(
                    modifier = Modifier.padding(end = 1.dp).weight(1f),
                    painter = painterResource(id = item.image),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .padding(start = 1.dp)
                        .weight(1f),
                    painter = painterResource(id = item.image),
                    contentDescription = null
                )
            }
        }
    }
}

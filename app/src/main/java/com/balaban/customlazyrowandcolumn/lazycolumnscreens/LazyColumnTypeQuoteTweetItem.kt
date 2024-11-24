package com.balaban.customlazyrowandcolumn.lazycolumnscreens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balaban.customlazyrowandcolumn.LazyViewManager
import com.balaban.customlazyrowandcolumn.R
import com.balaban.customlazyrowandcolumn.models.QuoteTweetScreenItem
import com.balaban.customlazyrowandcolumn.models.SplitScreenItem
import com.balaban.customlazyrowandcolumn.scren.ProfileItem
import com.balaban.customlazyrowandcolumn.scren.VerifiedImage

@Composable
fun LazyViewManager.LazyColumnTypeQuoteTweetItem(
    item: QuoteTweetScreenItem
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        itemLongClickListener?.invoke(item)
                    },
                    onTap = {
                        itemClickListener?.invoke(item)
                    }
                )
            }
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            ProfileItem()
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Hesen B.", color = Color.Black, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(4.dp))
                    VerifiedImage(modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "@hesenblbn101 · 2m", color = Color.Gray, fontSize = 12.sp)
                }
                Text(text = "Let's look this tweet", color = Color.Black)
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
                    .padding(top = 12.dp)
            ) {
                val list by remember { mutableStateOf(List(item.imageList.size) { R.drawable.image }) }
                LazyColumnTypeSplitScreenItem(
                    SplitScreenItem(list, 0),
                    isQuoteItem = true
                )
            }
        }
    }
}
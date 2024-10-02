package com.balaban.customlazyrowandcolumn.lazycolumnscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balaban.customlazyrowandcolumn.models.HeaderItem
import kotlin.random.Random

@Composable
fun LazyColumnTypeHeaderItem(item: HeaderItem) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = item.header,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = item.description.substring(
                0,
                minOf(Random.nextInt(0, item.description.length), item.description.length)
            ),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
    }
}
package com.balaban.customlazyrowandcolumn.lazycolumnscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balaban.customlazyrowandcolumn.models.BannerScreenItem

@Composable
fun LazyColumnTypeBannerItem(item: BannerScreenItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(brush = Brush.horizontalGradient(
                colors = listOf(Color.Blue, Color.Green)
            ))
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = item.text,
            color = Color.White,
            fontSize = 24.sp
        )
    }
}




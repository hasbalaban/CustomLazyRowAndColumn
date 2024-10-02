package com.balaban.customlazyrowandcolumn.scren

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.balaban.customlazyrowandcolumn.R

@Composable
fun ProfileItem() {
    Image(
        painter = painterResource(id = R.drawable.ben), // Replace with the logo
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
    )
}
package com.balaban.customlazyrowandcolumn.scren

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

@Composable
fun VerifiedImage(modifier: Modifier = Modifier){
    Image(
        imageVector = Icons.Filled.Check,
        contentDescription = "Verified",
        modifier = Modifier
            .padding(start = 4.dp)
            .then(modifier)
            .clip(CircleShape)
            .background(Color(0xff1d9bf0))
            .padding(2.dp),
        colorFilter = ColorFilter
            .tint(Color.White)
    )
}
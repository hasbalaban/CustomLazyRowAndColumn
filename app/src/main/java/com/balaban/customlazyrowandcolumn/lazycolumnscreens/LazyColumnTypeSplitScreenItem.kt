package com.balaban.customlazyrowandcolumn.lazycolumnscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.balaban.customlazyrowandcolumn.models.SplitScreenItem

@Composable
fun LazyColumnTypeSplitScreenItem(item: SplitScreenItem) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = item.image),
                contentDescription = null
            )
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = item.image),
                contentDescription = null
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = item.image),
                contentDescription = null
            )
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = item.image),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "This is a comment",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

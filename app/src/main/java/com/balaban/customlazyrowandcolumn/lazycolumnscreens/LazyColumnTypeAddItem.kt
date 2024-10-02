package com.balaban.customlazyrowandcolumn.lazycolumnscreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.balaban.customlazyrowandcolumn.models.AddScreenItem
import kotlin.random.Random

@Composable
fun LazyColumnTypeAddItem(item: AddScreenItem) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current



    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "Ads",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .horizontalScroll(state = scrollState)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in 0..Random.nextInt(2, 10)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = "Genkai Warrior Hoodie",
                        modifier = Modifier
                            .width(width = 250.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        text = "Limited Edition Genkai Warrior Hoodie ",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "CyberKongz x 9dcc",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Button(
            onClick = {
                Toast.makeText(context, "clicked", Toast.LENGTH_LONG).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text(text = "SHOP NOW!")
        }
    }
}

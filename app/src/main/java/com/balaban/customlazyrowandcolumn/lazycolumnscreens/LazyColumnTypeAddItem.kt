package com.balaban.customlazyrowandcolumn.lazycolumnscreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balaban.customlazyrowandcolumn.ClickListeners
import com.balaban.customlazyrowandcolumn.models.AddScreenItem
import com.balaban.customlazyrowandcolumn.models.ItemType
import kotlin.random.Random

@Composable
fun LazyColumnTypeAddItem(item: AddScreenItem, setOnListeners : (ClickListeners) -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .pointerInput(Unit) {
            detectTapGestures(
                onLongPress = {
                    setOnListeners.invoke(ClickListeners.ItemLongClickListener(item as ItemType))
                },
                onTap = {
                    setOnListeners.invoke(ClickListeners.ItemClickListener(item as ItemType))
                }
            )
        }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Filled.AccountCircle, // Replace with the logo
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))


            Text(
                text = "Reuters ",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier,
                text = "· Ads",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }


        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            LazyRow{
                items(
                    count = Random.nextInt(6, 10)
                ){
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

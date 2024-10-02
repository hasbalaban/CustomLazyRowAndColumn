package com.balaban.customlazyrowandcolumn

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balaban.customlazyrowandcolumn.ui.theme.CustomLazyRowAndColumnTheme
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomLazyRowAndColumnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier){
        ListWithDifferentContentTypes()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val item = AddScreenItem(image = R.drawable.image)
    CustomLazyRowAndColumnTheme {
        Column(modifier = Modifier.padding(12.dp)){
            AddScreen(item)
        }
    }
}


abstract class ViewHolder<T>()  {
    var itemClickListener: ((item: ItemType, position: Int) -> Unit)? = null
    var itemClickListenerWithView: ((item: ItemType, view: Int) -> Unit)? = null
    var itemLongClickListener: ((item: ItemType, position: Int) -> Boolean)? = null
    var itemClickListenerWithParams: ((item: ItemType, position: Int, obj:Any) -> Unit)? = null
    abstract fun bind(item: T)
    open fun onAttachAdapter() {}
    open fun onDetachAdapter() {}
}


data class TextItem(val text: String) : ItemType(ContentTypes.TYPE_TEXT)
data class HeaderItem(val header: String, val description : String) : ItemType(ContentTypes.TYPE_HEADER)
data class AddScreenItem(@DrawableRes val image: Int) : ItemType(ContentTypes.TYPE_ADD_ITEM)
data class SplitScreenItem(@DrawableRes val image: Int) : ItemType(ContentTypes.TYPE_SPLITTED_SCREEN)

open class ItemType (val type: ContentTypes)


val items = listOf(
    // TextItem("TextItem 1"),
    // TextItem("TextItem 2"),
    AddScreenItem(image = R.drawable.image),
    SplitScreenItem(image = R.drawable.image),
    HeaderItem(header = "HeaderItem 1", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),

    SplitScreenItem(image = R.drawable.image),
    AddScreenItem(image = R.drawable.image),
    HeaderItem(header = "HeaderItem 2", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n")
)
@Composable
fun ListWithDifferentContentTypes() {
    val list by remember {
        mutableStateOf((items + items + items + items))
    }
    LazyColumn{
        itemsIndexed(
            items = list.shuffled(),
            contentType = { _, item -> item.type },
        ) { index, item ->

            when (item.type) {
                ContentTypes.TYPE_TEXT -> {
                    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                        TextItem(item = item as TextItem)
                    }
                }

                ContentTypes.TYPE_HEADER -> {
                    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                        HeaderItem(item = item as HeaderItem)
                    }
                }

                ContentTypes.TYPE_ADD_ITEM -> {
                    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                        AddScreen(item = item as AddScreenItem)
                    }
                }

                ContentTypes.TYPE_SPLITTED_SCREEN -> {
                    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                        SplitScreenItem(item = item as SplitScreenItem)
                    }
                }
            }

            if (index < list.size - 1) {
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp), color = Color.Gray.copy(alpha = 0.2f), thickness = 1.dp)
            }
        }
    }
}


@Composable
private fun TextItem(item: TextItem) {
    Text(
        text = item.text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(8.dp)
    )
}


@Composable
private fun HeaderItem(item: HeaderItem) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
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
            text = item.description.substring(0, minOf(Random.nextInt(0, item.description.length), item.description.length)),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
    }
}


@Composable
private fun AddScreen(item: AddScreenItem) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current



    Column (modifier = Modifier.fillMaxWidth()){
        Row(verticalAlignment = Alignment.CenterVertically){
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
            for (i in 0..Random.nextInt(2,10)){
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
                    Text(  modifier = Modifier
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

@Composable
private fun SplitScreenItem(item: SplitScreenItem) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(modifier = Modifier.fillMaxWidth()){
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
        Row(modifier = Modifier.fillMaxWidth()){
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



enum class ContentTypes {
    TYPE_TEXT,
    TYPE_HEADER,
    TYPE_ADD_ITEM,
    TYPE_SPLITTED_SCREEN
}
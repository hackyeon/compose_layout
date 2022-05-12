package com.hackyeon.compose_layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackyeon.compose_layout.ui.theme.Compose_layoutTheme

class ScaffoldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_layoutTheme {
                LayoutCodeLab()
            }
        }
    }
}

@Composable
fun LayoutCodeLab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutCodeLab")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent1(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent1(modifier: Modifier){
    MyOwnColumn(modifier.padding(8.dp)) {
        Text(text = "MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }

//    Column(modifier = modifier.padding(8.dp)) {
//        Text(text = "MyOwnColumn")
//        Text("places items")
//        Text("vertically.")
//        Text("We've done it by hand!")
//    }
}

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        var yPosition = 0

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LayoutCodeLabPreView() {
    Compose_layoutTheme {
        LayoutCodeLab()
    }
}
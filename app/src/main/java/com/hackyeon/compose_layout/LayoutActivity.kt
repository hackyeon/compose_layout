package com.hackyeon.compose_layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hackyeon.compose_layout.ui.theme.Compose_layoutTheme

class LayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_layoutTheme {
            }
        }
    }
}




fun Modifier.firstBaseLineToTop(
    firstBaseLineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseLine = placeable[FirstBaseline]

        val placeableY = firstBaseLineToTop.roundToPx() - firstBaseLine
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview
@Composable
fun TextWithPaddingToBaseLinePreView() {
    Compose_layoutTheme {
        Text(text = "Hi there!", Modifier.firstBaseLineToTop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreView() {
    Compose_layoutTheme {
        Text(text = "Hi there!", Modifier.padding(top = 32.dp))
    }
}
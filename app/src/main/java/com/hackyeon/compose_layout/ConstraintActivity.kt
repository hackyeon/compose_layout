package com.hackyeon.compose_layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import com.hackyeon.compose_layout.ui.theme.Compose_layoutTheme

class ConstraintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_layoutTheme {
                DecoupledConstraintLayout()
            }
        }
    }
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if(maxWidth < maxHeight) {
            decoupledConstraints(16.dp)
        } else {
            decoupledConstraints(32.dp)
        }
        ConstraintLayout(constraints) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("button")
            ) {
                Text(text = "Button")
            }

            Text(text = "Text", modifier = Modifier.layoutId("text"))
        }


    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()
        
        val guideLine = createGuidelineFromStart(fraction = 0.5f)
        Text(text = "This is a very very very very very very very long text",
        modifier = Modifier.constrainAs(text) {
            linkTo(start = guideLine, end = parent.end)
            width = Dimension.preferredWrapContent
        })
        
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {

        val (button1,button2,  text) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(text = "Button1")
        }

        Text(text = "Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)


//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
            // 위에 스타트 엔드 설정이랑 같다고 볼수있음음
//           centerHorizontallyTo(parent)
        })

        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text(text = "Button2")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    Compose_layoutTheme {
        DecoupledConstraintLayout()
    }
}
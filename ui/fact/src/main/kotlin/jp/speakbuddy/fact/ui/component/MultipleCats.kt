package jp.speakbuddy.fact.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun MultipleCats(
    isShowing: Boolean,
    modifier: Modifier = Modifier,
    catOneWidth: Dp = 100.dp,
    catTwoWidth: Dp = 75.dp,
    catThreeWidth: Dp = 30.dp
) {
    val catOneOffset by animateDpAsState(
        targetValue = if (isShowing) catOneWidth * 0.3f else catOneWidth,
        animationSpec = tween(1000),
        label = "cat 1 animation"
    )

    val catTwoOffset by animateDpAsState(
        targetValue = if (isShowing) catTwoWidth * 0.55f else catTwoWidth,
        animationSpec = tween(750),
        label = "cat 2 animation"
    )

    val catThreeOffset by animateDpAsState(
        targetValue = if (isShowing) {
            (-125).dp + catThreeWidth * 0.5f
        } else {
            (-125).dp + catThreeWidth * 1f
        },
        animationSpec = tween(500),
        label = "cat 3 animation"
    )

    Box(modifier = modifier) {
        Cat(
            modifier = Modifier
                .align(Alignment.TopStart)
                .rotate(180f)
                .width(catOneWidth)
                .offset(
                    x = (-20).dp,
                    y = catOneOffset
                ),
            aspectRatio = 1.15f
        )

        Cat(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .rotate(-90f)
                .width(catTwoWidth)
                .offset(
                    x = 35.dp,
                    y = catTwoOffset
                ),
            aspectRatio = 1.5f
        )

        Cat(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .width(catThreeWidth)
                .offset(
                    x = 125.dp / -2f + catThreeWidth / 2f,
                    y = catThreeOffset
                ),
            aspectRatio = 1.5f
        )
    }
}

package jp.speakbuddy.fact.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import jp.speakbuddy.catfact.ui.fact.R

@Composable
internal fun CatWithLength(
    length: Int?,
    modifier: Modifier = Modifier,
    width: Dp = 125.dp,
    height: Dp = 150.dp
) {
    Box(
        modifier = modifier.size(
            width = width,
            height = height,
        )
    ) {
        if (length != null) {
            Image(
                painter = painterResource(id = R.drawable.img_speech_bubble),
                contentDescription = stringResource(id = R.string.cd_speech),
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )

            Text(
                text = stringResource(id = R.string.length, length),
                modifier = Modifier
                    .size(
                        width = width / 2f,
                        height = 40.dp
                    )
                    .offset(y = (-40).dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Cat(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

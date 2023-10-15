package jp.speakbuddy.fact.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import jp.speakbuddy.catfact.ui.fact.R

@Composable
internal fun Cat(
    modifier: Modifier = Modifier,
    aspectRatio: Float = 1f
) {
    Image(
        painter = painterResource(id = R.drawable.img_cat),
        contentDescription = stringResource(id = R.string.cd_cat),
        modifier = modifier.aspectRatio(aspectRatio),
        contentScale = ContentScale.FillBounds,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
    )
}

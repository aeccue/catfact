package jp.speakbuddy.fact.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.speakbuddy.catfact.ui.LocalSpacing
import jp.speakbuddy.catfact.ui.fact.R
import jp.speakbuddy.fact.ui.component.CatWithLength
import jp.speakbuddy.fact.ui.component.MultipleCats
import jp.speakbuddy.fact.ui.viewmodel.FactUIState
import jp.speakbuddy.fact.ui.viewmodel.FactViewModel

@Composable
fun FactScreen(
    modifier: Modifier = Modifier,
    viewModel: FactViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = LocalSpacing.current.extraLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = LocalSpacing.current.extraLarge,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(
                text = stringResource(id = R.string.title),
                style = MaterialTheme.typography.titleLarge
            )

            when (val state = uiState) {
                is FactUIState.Data -> Text(
                    text = state.fact,
                    style = MaterialTheme.typography.bodyLarge
                )

                FactUIState.Loading -> CircularProgressIndicator()
            }
        }

        MultipleCats(
            isShowing = (uiState as? FactUIState.Data)?.showMultipleCats ?: false,
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalSpacing.current.medium)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.instructions),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.Bottom)
                    .padding(LocalSpacing.current.extraLarge),
                color = LocalContentColor.current.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
            CatWithLength(
                length = (uiState as? FactUIState.Data)?.let {
                    if (it.showLength) it.length else null
                },
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = viewModel::refresh
                    )
            )
        }
    }
}

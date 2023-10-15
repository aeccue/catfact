package jp.speakbuddy.fact.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.speakbuddy.catfact.ui.LocalSpacing
import jp.speakbuddy.catfact.ui.fact.R
import jp.speakbuddy.fact.ui.viewmodel.FactUIState
import jp.speakbuddy.fact.ui.viewmodel.FactViewModel

@Composable
fun FactScreen(
    viewModel: FactViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = viewModel::refresh
            )
            .padding(LocalSpacing.current.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = LocalSpacing.current.medium,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = stringResource(id = R.string.title),
            style = MaterialTheme.typography.titleLarge
        )

        when (val state = uiState) {
            is FactUIState.Data -> {
                Text(
                    text = state.fact,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            FactUIState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}

package jp.speakbuddy.fact.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.catfact.domain.usecase.GetFactWithMultipleCats
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

internal sealed interface FactUIState {

    data class Data(
        val fact: String,
        val length: Int,
        val showMultipleCats: Boolean,
        val showLength: Boolean
    ) : FactUIState

    data object Loading : FactUIState
}

private const val SHOW_LENGTH_THRESHOLD = 100

@HiltViewModel
internal class FactViewModel @Inject constructor(
    getFactWithMultipleCats: GetFactWithMultipleCats
) : ViewModel() {

    private val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val data: StateFlow<FactUIState.Data> =
        getFactWithMultipleCats()
            .mapLatest {
                FactUIState.Data(
                    fact = it.fact,
                    length = it.length,
                    showMultipleCats = it.hasMultipleCats,
                    showLength = it.length >= SHOW_LENGTH_THRESHOLD
                )
            }
            .onEach {
                isLoading.value = false
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = FactUIState.Data(
                    fact = "",
                    length = 0,
                    showMultipleCats = false,
                    showLength = false
                )
            )

    val uiState: StateFlow<FactUIState> =
        data.combine(isLoading) { newData, loading ->
            if (loading) {
                FactUIState.Loading
            } else {
                newData
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = FactUIState.Loading
        )
}

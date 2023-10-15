package jp.speakbuddy.catfact.ui

import org.junit.Test

class FactViewModelTest {

    private val viewModel = FactViewModel()

    @Test
    fun updateFact() {
        var loading = true
        val initialFact = "initial"
        var fact = initialFact

        fact = viewModel.updateFact { loading = false }

        assert(!loading)
        assert(fact != initialFact)
    }
}

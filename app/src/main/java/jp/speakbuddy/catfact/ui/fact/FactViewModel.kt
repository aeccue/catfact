package jp.speakbuddy.catfact.ui.fact

import androidx.lifecycle.ViewModel
import jp.speakbuddy.catfact.network.FactServiceProvider
import kotlinx.coroutines.runBlocking

class FactViewModel : ViewModel() {
    fun updateFact(completion: () -> Unit): String =
        runBlocking {
            try {
                FactServiceProvider.provide().getFact().fact
            } catch (e: Throwable) {
                "something went wrong. error = ${e.message}"
            }.also { completion() }
        }
}

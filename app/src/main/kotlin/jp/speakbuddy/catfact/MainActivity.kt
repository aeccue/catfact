package jp.speakbuddy.catfact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import jp.speakbuddy.catfact.ui.CatFactTheme
import jp.speakbuddy.fact.ui.FactScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatFactTheme {
               FactScreen()
            }
        }
    }
}

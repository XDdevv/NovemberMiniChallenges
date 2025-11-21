package zed.rainxch.novemberminichallenges

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.global_deals.data.AndroidLanguagePreferences
import zed.rainxch.novemberminichallenges.global_deals.presentation.models.localeKey
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context) {
        val context = applyLanguage(newBase)
        super.attachBaseContext(context)
        ContextProvider.context = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        ContextProvider.context = this

        setContent {
            App()
        }
    }
}

private fun applyLanguage(context: Context): Context {
    val languagePreferences = AndroidLanguagePreferences(context)
    val languageCode = languagePreferences.getCurrentLanguage().localeKey()

    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)

    return context.createConfigurationContext(config)
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
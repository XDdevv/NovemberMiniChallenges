package zed.rainxch.novemberminichallenges.global_deals.data

import android.content.Context
import androidx.core.content.edit
import zed.rainxch.novemberminichallenges.ContextProvider
import zed.rainxch.novemberminichallenges.global_deals.domain.LanguagePreferences
import zed.rainxch.novemberminichallenges.global_deals.domain.model.Language
import zed.rainxch.novemberminichallenges.global_deals.presentation.models.localeKey

actual fun languagePreferences(): LanguagePreferences {
    return AndroidLanguagePreferences(ContextProvider.context!!)
}

class AndroidLanguagePreferences(
    context: Context
) : LanguagePreferences {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    override fun changeLanguage(language: Language) {
        prefs.edit {
            putString("language", language.localeKey())
        }
    }

    override fun getCurrentLanguage(): Language {
        return Language.entries.find {
            it.localeKey() == (prefs.getString("language", null) ?: Language.English.localeKey())
        } ?: Language.English
    }

}
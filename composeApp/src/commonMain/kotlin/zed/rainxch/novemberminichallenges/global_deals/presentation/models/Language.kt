package zed.rainxch.novemberminichallenges.global_deals.presentation.models

import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.ic_flag_arab
import novemberminichallenges.composeapp.generated.resources.ic_flag_english
import novemberminichallenges.composeapp.generated.resources.ic_flag_spanish
import org.jetbrains.compose.resources.DrawableResource

enum class Language {
    Arabic,
    Spanish,
    English;

    fun localeKey(): String {
        return when (this) {
            Arabic -> "ar"
            Spanish -> "es"
            English -> "en"
        }
    }

    fun displayText(): String {
        return when (this) {
            Arabic -> "العربية"
            Spanish -> "Español"
            English -> "English"
        }
    }

    fun image(): DrawableResource {
        return when (this) {
            Arabic -> Res.drawable.ic_flag_arab
            Spanish -> Res.drawable.ic_flag_spanish
            English -> Res.drawable.ic_flag_english
        }
    }
}
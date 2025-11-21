package zed.rainxch.novemberminichallenges.global_deals.domain

import zed.rainxch.novemberminichallenges.global_deals.domain.model.Language

interface LanguagePreferences {
    fun changeLanguage(language: Language)
    fun getCurrentLanguage() : Language
}
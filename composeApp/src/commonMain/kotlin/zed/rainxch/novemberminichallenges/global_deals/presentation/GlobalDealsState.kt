package zed.rainxch.novemberminichallenges.global_deals.presentation

import zed.rainxch.novemberminichallenges.global_deals.domain.model.Language
import zed.rainxch.novemberminichallenges.global_deals.presentation.models.Product

data class GlobalDealsState(
    val selectedLanguage: Language = Language.English,
    val popupLanguages: List<Language> = emptyList(),
    val products: List<Product> = emptyList(),
    val isLanguageDropdownVisible: Boolean = false,
)
package zed.rainxch.novemberminichallenges.global_deals.presentation

import zed.rainxch.novemberminichallenges.global_deals.domain.model.Language

sealed interface GlobalDealsAction {
    data class OnLanguageSelected(val language: Language) : GlobalDealsAction
    data object OnSelectedLanguageClick : GlobalDealsAction
    data object OnLanguagePopupClose : GlobalDealsAction
}
package zed.rainxch.novemberminichallenges.sticky_ad.presentation

import zed.rainxch.novemberminichallenges.sticky_ad.presentation.models.StickyAdList

data class StickyAdState(
    val items: List<StickyAdList> = emptyList()
)
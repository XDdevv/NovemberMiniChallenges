package zed.rainxch.novemberminichallenges.sticky_ad.presentation

import zed.rainxch.novemberminichallenges.sticky_ad.presentation.models.StickyAdList

sealed interface StickyAdAction {
    data class OnBannerCloseClick(val ad: StickyAdList.StickyAdDiscountBanner) : StickyAdAction
}
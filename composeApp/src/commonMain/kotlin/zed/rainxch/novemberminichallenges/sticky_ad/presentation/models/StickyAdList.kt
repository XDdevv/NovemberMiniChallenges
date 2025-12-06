package zed.rainxch.novemberminichallenges.sticky_ad.presentation.models

import org.jetbrains.compose.resources.DrawableResource

sealed interface StickyAdList {
    data class StickyAdProduct(
        val imageRes: DrawableResource,
        val title: String,
        val price: String,
    ) : StickyAdList

    data class StickyAdDiscountBanner(
        val title: String,
        val discount: String,
        val isVisible: Boolean = true
    ) : StickyAdList
}

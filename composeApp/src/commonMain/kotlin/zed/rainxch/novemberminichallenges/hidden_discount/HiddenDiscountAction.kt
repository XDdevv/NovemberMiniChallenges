package zed.rainxch.novemberminichallenges.hidden_discount

import zed.rainxch.novemberminichallenges.hidden_discount.model.CartItem

sealed interface HiddenDiscountAction {
    data class OnCopyPromoCode(val promoCode: String) : HiddenDiscountAction
    data class OnPromoCodeChange(val promoCode: String) : HiddenDiscountAction
    data object OnApplyPromoCodeClick : HiddenDiscountAction
    data class OnCartItemPromoCollapsed(val cartItem: CartItem) : HiddenDiscountAction
    data class OnCartItemPromoExpanded(val cartItem: CartItem) : HiddenDiscountAction
}
package zed.rainxch.novemberminichallenges.hidden_discount

import zed.rainxch.novemberminichallenges.hidden_discount.model.PromoCode
import zed.rainxch.novemberminichallenges.hidden_discount.model.CartItem

data class HiddenDiscountState(
    val carts: List<CartItem> = emptyList(),
    val promoCode: PromoCode? = null,
    val promoCodeInput: String = "",
    val promoCodeInputError: String? = null
)
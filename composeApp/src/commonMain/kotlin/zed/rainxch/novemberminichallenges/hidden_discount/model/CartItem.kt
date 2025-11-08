package zed.rainxch.novemberminichallenges.hidden_discount.model

import org.jetbrains.compose.resources.DrawableResource

data class CartItem(
    val imageRes: DrawableResource,
    val name: String,
    val shortDescription: String,
    val price: Double,
    val isDiscountRevealed: Boolean,
)

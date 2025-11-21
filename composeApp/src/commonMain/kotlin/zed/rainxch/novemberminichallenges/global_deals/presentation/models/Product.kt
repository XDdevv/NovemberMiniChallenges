package zed.rainxch.novemberminichallenges.global_deals.presentation.models

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class Product(
    val id: Int,
    val nameRes: StringResource,
    val productImage: DrawableResource,
    val originalPriceRes: StringResource,
    val discountPriceRes: StringResource,
    val discountPercent: Int,
)

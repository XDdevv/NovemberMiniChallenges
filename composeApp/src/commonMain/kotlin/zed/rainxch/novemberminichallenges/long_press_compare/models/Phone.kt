package zed.rainxch.novemberminichallenges.long_press_compare.models

import org.jetbrains.compose.resources.DrawableResource

data class Phone(
    val modelName: String,
    val imageRes: DrawableResource,
    val discount: Float? = null,
    val price: Double,
    val display: String,
    val mainCamera: String,
    val frontCamera: String,
    val processor: String,
    val ram: String,
    val storage: String,
    val batteryMAH: String
)

package zed.rainxch.novemberminichallenges.hidden_discount.model

import kotlin.jvm.JvmExposeBoxed
import kotlin.jvm.JvmInline

@OptIn(ExperimentalStdlibApi::class)
@JvmInline
@JvmExposeBoxed
value class PromoCode(
    val discount: Float,
)
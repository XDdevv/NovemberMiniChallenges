package zed.rainxch.novemberminichallenges.hidden_discount

sealed interface HiddenDiscountEvents {
    data class OnMessage(val message: String) : HiddenDiscountEvents
}
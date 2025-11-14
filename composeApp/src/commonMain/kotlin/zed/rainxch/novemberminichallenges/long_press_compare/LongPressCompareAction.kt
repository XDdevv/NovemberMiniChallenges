package zed.rainxch.novemberminichallenges.long_press_compare

import zed.rainxch.novemberminichallenges.long_press_compare.models.LongPressPhone

sealed interface LongPressCompareAction {
    data class OnPhoneLongClick(val phone: LongPressPhone) : LongPressCompareAction
    data class OnPhoneClick(val phone: LongPressPhone) : LongPressCompareAction
    data object OnClearSelectionClick : LongPressCompareAction
    data object OnComparisonDialogClose : LongPressCompareAction
    data object OnCompareClick : LongPressCompareAction
}
package zed.rainxch.novemberminichallenges.long_press_compare

import zed.rainxch.novemberminichallenges.long_press_compare.models.LongPressPhone
import zed.rainxch.novemberminichallenges.long_press_compare.models.Phone

data class LongPressCompareState(
    val phones: List<LongPressPhone> = listOf(),
    val selectedPhones: Pair<Phone?, Phone?> = null to null,
    val isShowingComparison: Boolean = false,
)

/*
Phone(
        imageRes = Res.drawable.google_pixel_9,
        price = 20.0,
        display = "Hi",
        mainCamera = "21",
        frontCamera = "21",
        processor = "Dragon",
        ram = "31 gb",
        storage = "Hi",
        batteryMAH = "MAH"
    )
 */
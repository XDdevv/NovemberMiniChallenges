package zed.rainxch.novemberminichallenges.long_press_compare.utils

import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.google_pixel_9
import novemberminichallenges.composeapp.generated.resources.google_pixel_9_pro
import novemberminichallenges.composeapp.generated.resources.iPhone_15_pro
import novemberminichallenges.composeapp.generated.resources.one_plus_12
import novemberminichallenges.composeapp.generated.resources.samsung_galaxy_s24_plus
import novemberminichallenges.composeapp.generated.resources.xiaomi_14
import zed.rainxch.novemberminichallenges.long_press_compare.models.LongPressPhone
import zed.rainxch.novemberminichallenges.long_press_compare.models.Phone

val phones = listOf(
    Phone(
        modelName = "Google Pixel 9",
        imageRes = Res.drawable.google_pixel_9,
        price = 799.0,
        display = "6.3\"",
        mainCamera = "50MP",
        frontCamera = "12MP",
        processor = "Tensor G4",
        ram = "8GB",
        storage = "128GB",
        batteryMAH = "4600mAh"
    ),
    Phone(
        modelName = "Google Pixel 9 Pro",
        imageRes = Res.drawable.google_pixel_9_pro,
        price = 999.0,
        display = "6.7\"",
        mainCamera = "50MP",
        frontCamera = "12MP",
        processor = "Tensor G4",
        ram = "12GB",
        storage = "256GB",
        batteryMAH = "5100mAh"
    ),
    Phone(
        modelName = "Samsung Galaxy S24+",
        imageRes = Res.drawable.samsung_galaxy_s24_plus,
        price = 899.0,
        discount = ((999.0 - 899.0) / 999.0 * 100).toFloat(),
        display = "6.7\"",
        mainCamera = "50MP",
        frontCamera = "12MP",
        processor = "Snapdragon 8 Gen 3",
        ram = "12GB",
        storage = "256GB",
        batteryMAH = "4900mAh"
    ),
    Phone(
        modelName = "OnePlus 12",
        imageRes = Res.drawable.one_plus_12,
        price = 799.0,
        discount = ((899.0 - 799.0) / 899.0 * 100).toFloat(),
        display = "6.8\"",
        mainCamera = "50MP",
        frontCamera = "32MP",
        processor = "Snapdragon 8 Gen 3",
        ram = "12GB",
        storage = "256GB",
        batteryMAH = "5400mAh"
    ),
    Phone(
        modelName = "iPhone 15 Pro",
        imageRes = Res.drawable.iPhone_15_pro,
        price = 1099.0,
        display = "6.1\"",
        mainCamera = "48MP",
        frontCamera = "12MP",
        processor = "A17 Pro",
        ram = "8GB",
        storage = "256GB",
        batteryMAH = "4350mAh"
    ),
    Phone(
        modelName = "Xiaomi 14",
        imageRes = Res.drawable.xiaomi_14,
        price = 699.0,
        discount = ((799.0 - 699.0) / 799.0 * 100).toFloat(),
        display = "6.4\"",
        mainCamera = "50MP",
        frontCamera = "32MP",
        processor = "Snapdragon 8 Gen 3",
        ram = "12GB",
        storage = "256GB",
        batteryMAH = "4610mAh"
    )
)

fun List<Phone>.toLongPressPhone(): List<LongPressPhone> {
    return this.map { LongPressPhone(it) }
}
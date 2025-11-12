package zed.rainxch.novemberminichallenges.long_press_compare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.google_pixel_9
import novemberminichallenges.composeapp.generated.resources.google_pixel_9_pro
import novemberminichallenges.composeapp.generated.resources.iPhone_15_pro
import novemberminichallenges.composeapp.generated.resources.one_plus_12
import novemberminichallenges.composeapp.generated.resources.samsung_galaxy_s24_plus
import novemberminichallenges.composeapp.generated.resources.xiaomi_14
import zed.rainxch.novemberminichallenges.long_press_compare.models.LongPressPhone
import zed.rainxch.novemberminichallenges.long_press_compare.models.Phone

class LongPressCompareViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(LongPressCompareState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadData()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LongPressCompareState()
        )

    private fun loadData() {
        viewModelScope.launch {
            val phones = listOf(
                LongPressPhone(
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
                    )
                ),
                LongPressPhone(
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
                    )
                ),
                LongPressPhone(
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
                    )
                ),
                LongPressPhone(
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
                    )
                ),
                LongPressPhone(
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
                    )
                ),
                LongPressPhone(
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
            )

            _state.update {
                it.copy(
                    phones = phones
                )
            }
        }
    }

    fun onAction(action: LongPressCompareAction) {
        when (action) {
            LongPressCompareAction.OnClearSelectionClick -> {
                _state.update {
                    it.copy(
                        selectedPhones = null to null,
                        phones = it.phones.map { it.copy(selected = false) }
                    )
                }
            }

            is LongPressCompareAction.OnPhoneLongClick -> {
                val clickedPhone = action.phone.phone
                val currentSelected = _state.value.selectedPhones

                val newSelected = when {
                    currentSelected.first == null -> clickedPhone to currentSelected.second
                    currentSelected.second == null -> currentSelected.first to clickedPhone
                    else -> currentSelected.first to clickedPhone
                }

                _state.update { state ->
                    state.copy(
                        selectedPhones = newSelected,
                        phones = state.phones.map { phoneItem ->
                            phoneItem.copy(
                                selected = listOf(newSelected.first, newSelected.second)
                                    .any { it?.modelName == phoneItem.phone.modelName }
                            )
                        }
                    )
                }
            }

            is LongPressCompareAction.OnPhoneClick -> {
                val clickedPhone = action.phone.phone
                val currentSelected = _state.value.selectedPhones

                val newSelected = when {
                    currentSelected.first?.modelName == clickedPhone.modelName -> null to currentSelected.second
                    currentSelected.second?.modelName == clickedPhone.modelName -> currentSelected.first to null
                    else -> currentSelected
                }

                _state.update { state ->
                    state.copy(
                        selectedPhones = newSelected,
                        phones = state.phones.map { phoneItem ->
                            phoneItem.copy(
                                selected = listOf(newSelected.first, newSelected.second)
                                    .any { it?.modelName == phoneItem.phone.modelName }
                            )
                        }
                    )
                }
            }
        }
    }

}
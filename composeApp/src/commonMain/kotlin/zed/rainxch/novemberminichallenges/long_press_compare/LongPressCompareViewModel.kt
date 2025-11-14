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
import zed.rainxch.novemberminichallenges.long_press_compare.utils.phones
import zed.rainxch.novemberminichallenges.long_press_compare.utils.toLongPressPhone

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
            val phones = phones.toLongPressPhone()

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

            LongPressCompareAction.OnCompareClick -> {
                _state.update { it.copy(
                    isShowingComparison = true
                ) }
            }
            LongPressCompareAction.OnComparisonDialogClose -> {
                _state.update { it.copy(
                    isShowingComparison = false
                ) }
            }
        }
    }

}
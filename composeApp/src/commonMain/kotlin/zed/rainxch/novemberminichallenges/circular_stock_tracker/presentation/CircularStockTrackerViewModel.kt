package zed.rainxch.novemberminichallenges.circular_stock_tracker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CircularStockTrackerViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(CircularStockTrackerState())
    val state = _state.asStateFlow()

    fun onAction(action: CircularStockTrackerAction) {
        when (action) {
            CircularStockTrackerAction.OnBuyClick -> {
                _state.update {
                    it.copy(
                        remainingDiscountPrice = it.remainingDiscountPrice - 1
                    )
                }
            }
        }
    }

}
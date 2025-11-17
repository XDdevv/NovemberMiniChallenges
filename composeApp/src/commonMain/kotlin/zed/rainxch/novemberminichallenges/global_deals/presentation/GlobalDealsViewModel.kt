package zed.rainxch.novemberminichallenges.global_deals.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class GlobalDealsViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(GlobalDealsState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GlobalDealsState()
        )

    fun onAction(action: GlobalDealsAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}
package zed.rainxch.novemberminichallenges.hidden_discount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.buds
import zed.rainxch.novemberminichallenges.hidden_discount.model.CartItem
import zed.rainxch.novemberminichallenges.hidden_discount.model.PromoCode

class HiddenDiscountViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    companion object {
        const val VALID_PROMO_CODE = "BF2025"
    }

    private val _state = MutableStateFlow(HiddenDiscountState())
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
            initialValue = HiddenDiscountState()
        )

    private fun loadData() {
        viewModelScope.launch {
            val carts = listOf(
                CartItem(
                    imageRes = Res.drawable.buds,
                    name = "Google Pixel Buds Pro",
                    shortDescription = "Noise-cancelling wireless earbuds with rich sound and long battery life.",
                    price = 199.0,
                    isDiscountRevealed = false
                )
            )

            _state.update {
                it.copy(
                    carts = carts
                )
            }
        }
    }

    fun onAction(action: HiddenDiscountAction) {
        when (action) {
            HiddenDiscountAction.OnApplyPromoCodeClick -> {
                if (_state.value.promoCodeInput == VALID_PROMO_CODE) {
                    _state.update {
                        it.copy(
                            promoCode = PromoCode(25f)
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            promoCode = null,
                            promoCodeInputError = "Invalid code"
                        )
                    }
                }
            }

            is HiddenDiscountAction.OnPromoCodeChange -> {
                _state.update {
                    it.copy(
                        promoCodeInput = action.promoCode,
                        promoCodeInputError = null
                    )
                }
            }

            is HiddenDiscountAction.OnCartItemPromoCollapsed -> {
                _state.update { it.copy(
                    carts = it.carts.map { cartItem ->
                        if (action.cartItem.name == cartItem.name) {
                            cartItem.copy(isDiscountRevealed = false)
                        } else cartItem
                    }
                ) }
            }
            is HiddenDiscountAction.OnCartItemPromoExpanded -> {
                _state.update { it.copy(
                    carts = it.carts.map { cartItem ->
                        if (action.cartItem.name == cartItem.name) {
                            cartItem.copy(isDiscountRevealed = true)
                        } else cartItem
                    }
                ) }
            }

            else -> {}
        }
    }

}
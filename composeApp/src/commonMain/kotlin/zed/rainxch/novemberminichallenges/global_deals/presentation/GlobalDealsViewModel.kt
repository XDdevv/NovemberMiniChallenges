package zed.rainxch.novemberminichallenges.global_deals.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.product_1
import novemberminichallenges.composeapp.generated.resources.product_1_discount_price
import novemberminichallenges.composeapp.generated.resources.product_1_name
import novemberminichallenges.composeapp.generated.resources.product_1_original_price
import novemberminichallenges.composeapp.generated.resources.product_2
import novemberminichallenges.composeapp.generated.resources.product_2_discount_price
import novemberminichallenges.composeapp.generated.resources.product_2_name
import novemberminichallenges.composeapp.generated.resources.product_2_original_price
import novemberminichallenges.composeapp.generated.resources.product_3
import novemberminichallenges.composeapp.generated.resources.product_3_discount_price
import novemberminichallenges.composeapp.generated.resources.product_3_name
import novemberminichallenges.composeapp.generated.resources.product_3_original_price
import novemberminichallenges.composeapp.generated.resources.product_4
import novemberminichallenges.composeapp.generated.resources.product_4_discount_price
import novemberminichallenges.composeapp.generated.resources.product_4_name
import novemberminichallenges.composeapp.generated.resources.product_4_original_price
import novemberminichallenges.composeapp.generated.resources.product_5
import novemberminichallenges.composeapp.generated.resources.product_5_discount_price
import novemberminichallenges.composeapp.generated.resources.product_5_name
import novemberminichallenges.composeapp.generated.resources.product_5_original_price
import novemberminichallenges.composeapp.generated.resources.product_6
import novemberminichallenges.composeapp.generated.resources.product_6_discount_price
import novemberminichallenges.composeapp.generated.resources.product_6_name
import novemberminichallenges.composeapp.generated.resources.product_6_original_price
import novemberminichallenges.composeapp.generated.resources.product_7
import novemberminichallenges.composeapp.generated.resources.product_7_discount_price
import novemberminichallenges.composeapp.generated.resources.product_7_name
import novemberminichallenges.composeapp.generated.resources.product_7_original_price
import novemberminichallenges.composeapp.generated.resources.product_8
import novemberminichallenges.composeapp.generated.resources.product_8_discount_price
import novemberminichallenges.composeapp.generated.resources.product_8_name
import novemberminichallenges.composeapp.generated.resources.product_8_original_price
import zed.rainxch.novemberminichallenges.global_deals.domain.LanguagePreferences
import zed.rainxch.novemberminichallenges.global_deals.domain.model.Language
import zed.rainxch.novemberminichallenges.global_deals.presentation.models.Product

class GlobalDealsViewModel(
    private val languagePreferences: LanguagePreferences
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(GlobalDealsState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadProducts()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GlobalDealsState()
        )

    private fun loadProducts() {
        viewModelScope.launch {
            val products = listOf(
                Product(
                    id = 1,
                    nameRes = Res.string.product_1_name,
                    productImage = Res.drawable.product_1,
                    originalPriceRes = Res.string.product_1_original_price,
                    discountPriceRes = Res.string.product_1_discount_price,
                    discountPercent = 38
                ),
                Product(
                    id = 2,
                    nameRes = Res.string.product_2_name,
                    productImage = Res.drawable.product_2,
                    originalPriceRes = Res.string.product_2_original_price,
                    discountPriceRes = Res.string.product_2_discount_price,
                    discountPercent = 56
                ),
                Product(
                    id = 3,
                    nameRes = Res.string.product_3_name,
                    productImage = Res.drawable.product_3,
                    originalPriceRes = Res.string.product_3_original_price,
                    discountPriceRes = Res.string.product_3_discount_price,
                    discountPercent = 19
                ),
                Product(
                    id = 4,
                    nameRes = Res.string.product_4_name,
                    productImage = Res.drawable.product_4,
                    originalPriceRes = Res.string.product_4_original_price,
                    discountPriceRes = Res.string.product_4_discount_price,
                    discountPercent = 33
                ),
                Product(
                    id = 5,
                    nameRes = Res.string.product_5_name,
                    productImage = Res.drawable.product_5,
                    originalPriceRes = Res.string.product_5_original_price,
                    discountPriceRes = Res.string.product_5_discount_price,
                    discountPercent = 32
                ),
                Product(
                    id = 6,
                    nameRes = Res.string.product_6_name,
                    productImage = Res.drawable.product_6,
                    originalPriceRes = Res.string.product_6_original_price,
                    discountPriceRes = Res.string.product_6_discount_price,
                    discountPercent = 13
                ),
                Product(
                    id = 7,
                    nameRes = Res.string.product_7_name,
                    productImage = Res.drawable.product_7,
                    originalPriceRes = Res.string.product_7_original_price,
                    discountPriceRes = Res.string.product_7_discount_price,
                    discountPercent = 61
                ),
                Product(
                    id = 8,
                    nameRes = Res.string.product_8_name,
                    productImage = Res.drawable.product_8,
                    originalPriceRes = Res.string.product_8_original_price,
                    discountPriceRes = Res.string.product_8_discount_price,
                    discountPercent = 21
                )
            )

            val popupLanguages = Language.entries.toMutableList()
            val currentLanguage = languagePreferences.getCurrentLanguage()
            popupLanguages.remove(currentLanguage)

            _state.update {
                it.copy(
                    products = products,
                    popupLanguages = popupLanguages,
                    selectedLanguage = currentLanguage
                )
            }

        }
    }

    fun onAction(action: GlobalDealsAction) {
        when (action) {
            GlobalDealsAction.OnLanguagePopupClose -> {
                _state.update {
                    it.copy(
                        isLanguageDropdownVisible = false
                    )
                }
            }

            GlobalDealsAction.OnSelectedLanguageClick -> {
                _state.update {
                    it.copy(
                        isLanguageDropdownVisible = true
                    )
                }
            }

            is GlobalDealsAction.OnLanguageSelected -> {
                _state.update { state ->
                    val selectedLanguage = action.language
                    val currentLanguage = state.selectedLanguage

                    val newLanguages = state.popupLanguages.toMutableList()
                    newLanguages.remove(selectedLanguage)
                    newLanguages.add(currentLanguage)

                    state.copy(
                        selectedLanguage = selectedLanguage,
                        popupLanguages = newLanguages,
                        isLanguageDropdownVisible = false
                    )
                }
            }
        }
    }

}
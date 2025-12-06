package zed.rainxch.novemberminichallenges.sticky_ad.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.sticky_ad_1
import novemberminichallenges.composeapp.generated.resources.sticky_ad_10
import novemberminichallenges.composeapp.generated.resources.sticky_ad_11
import novemberminichallenges.composeapp.generated.resources.sticky_ad_2
import novemberminichallenges.composeapp.generated.resources.sticky_ad_3
import novemberminichallenges.composeapp.generated.resources.sticky_ad_4
import novemberminichallenges.composeapp.generated.resources.sticky_ad_6
import novemberminichallenges.composeapp.generated.resources.sticky_ad_7
import novemberminichallenges.composeapp.generated.resources.sticky_ad_8
import novemberminichallenges.composeapp.generated.resources.sticky_ad_9
import zed.rainxch.novemberminichallenges.sticky_ad.presentation.models.StickyAdList

class StickyAdViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(StickyAdState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadItems()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = StickyAdState()
        )

    private fun loadItems() {
        viewModelScope.launch {
            val items = listOf(
                StickyAdList.StickyAdProduct(
                    imageRes = Res.drawable.sticky_ad_1,
                    title = "Google Pixel 9 Pro",
                    price = "$999"
                ),
                StickyAdList.StickyAdProduct(
                    imageRes = Res.drawable.sticky_ad_2,
                    title = "Google Pixel 9",
                    price = "$799"
                ),
                StickyAdList.StickyAdDiscountBanner(
                    title = "Black Friday Deals",
                    discount = "-50%"
                ),
                StickyAdList.StickyAdProduct(
                    imageRes = Res.drawable.sticky_ad_3,
                    title = "Google Pixel 8 Pro",
                    price = "$899"
                ),
                StickyAdList.StickyAdProduct(
                    imageRes = Res.drawable.sticky_ad_4,
                    title = "Google Pixel 8",
                    price = "$699"
                ),
                StickyAdList.StickyAdProduct(
                    imageRes = Res.drawable.sticky_ad_6,
                    title = "Google Pixel Fold",
                    price = "$1799"
                ),
                StickyAdList.StickyAdProduct(
                    imageRes = Res.drawable.sticky_ad_7,
                    title = "Google Pixel Tablet",
                    price = "$499"
                ),
                StickyAdList.StickyAdProduct(
                    imageRes = Res.drawable.sticky_ad_8,
                    title = "Google Pixel Watch 2",
                    price = "$349"
                ),
                StickyAdList.StickyAdProduct(
                    imageRes = Res.drawable.sticky_ad_9,
                    title = "Google Pixel Buds Pro",
                    price = "$199"
                ),
                StickyAdList.StickyAdProduct(
                    imageRes = Res.drawable.sticky_ad_10,
                    title = "Google Nest Hub (2nd Gen)",
                    price = "$99"
                ),
                StickyAdList.StickyAdProduct(
                    imageRes = Res.drawable.sticky_ad_11,
                    title = "Google Nest Audio",
                    price = "$99"
                )
            )

            _state.update {
                it.copy(
                    items = items
                )
            }
        }
    }

    fun onAction(action: StickyAdAction) {
        when (action) {
            is StickyAdAction.OnBannerCloseClick -> {
                _state.update {
                    it.copy(
                        items = it.items.map { item ->
                            if (action.ad.title == (item as? StickyAdList.StickyAdDiscountBanner)?.title) {
                                item.copy(isVisible = false)
                            } else item
                        }
                    )
                }
            }
        }
    }

}
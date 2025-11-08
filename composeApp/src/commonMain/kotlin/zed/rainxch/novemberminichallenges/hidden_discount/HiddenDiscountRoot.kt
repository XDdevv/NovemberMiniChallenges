package zed.rainxch.novemberminichallenges.hidden_discount

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.core.presentation.design_system.HiddenDiscountColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.hidden_discount.components.CartItem
import zed.rainxch.novemberminichallenges.hidden_discount.components.CartPromoCode
import zed.rainxch.novemberminichallenges.hidden_discount.components.DiscountSnackbar
import zed.rainxch.novemberminichallenges.hidden_discount.components.SwipeableCartItem
import zed.rainxch.novemberminichallenges.rememberClipboardHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HiddenDiscountRoot(
    viewModel: HiddenDiscountViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val clipboardHelper = rememberClipboardHelper()
    val coroutineScope = rememberCoroutineScope()

    HiddenDiscountScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is HiddenDiscountAction.OnCopyPromoCode -> {
                    clipboardHelper.copy(action.promoCode)

                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Copied!")
                    }
                }

                else -> {
                    viewModel.onAction(action)
                }
            }
        },
        snackbarHostState = snackbarHostState
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HiddenDiscountScreen(
    state: HiddenDiscountState,
    onAction: (HiddenDiscountAction) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                DiscountSnackbar(data)
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Cart",
                        fontFamily = hostGroteskFont(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    )
                },
                navigationIcon = {
                    IconButton({}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back",
                            tint = HiddenDiscountColors.textPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = HiddenDiscountColors.bg
                )
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .navigationBarsPadding()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = state.promoCodeInput,
                        onValueChange = {
                            onAction(HiddenDiscountAction.OnPromoCodeChange(it))
                        },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text(
                                text = "Enter Promo Code",
                                color = HiddenDiscountColors.textDisabled,
                                fontFamily = hostGroteskFont(),
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        },
                        textStyle = TextStyle(
                            color = HiddenDiscountColors.textPrimary,
                            fontFamily = hostGroteskFont(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = HiddenDiscountColors.outline,
                            focusedBorderColor = HiddenDiscountColors.outline,
                            errorBorderColor = HiddenDiscountColors.discount,
                        ),
                        isError = state.promoCodeInputError != null,
                        supportingText = {
                            if (state.promoCodeInputError != null) {
                                Text(
                                    text = state.promoCodeInputError,
                                    color = HiddenDiscountColors.discount,
                                    fontFamily = hostGroteskFont(),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    )

                    Box(
                        modifier = Modifier
                            .height(56.dp)
                            .width(96.dp)
                            .background(HiddenDiscountColors.outline)
                            .clickable {
                                onAction(HiddenDiscountAction.OnApplyPromoCodeClick)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (state.promoCode != null) {
                                "Applied"
                            } else "Apply",
                            color = HiddenDiscountColors.textPrimary,
                            fontFamily = hostGroteskFont(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        },
        containerColor = HiddenDiscountColors.bg
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        ) {
            items(state.carts) { cart ->
                SwipeableCartItem(
                    isRevealed = cart.isDiscountRevealed,
                    onExpanded = {
                        onAction(HiddenDiscountAction.OnCartItemPromoExpanded(cart))
                    },
                    onCollapsed = {
                        onAction(HiddenDiscountAction.OnCartItemPromoCollapsed(cart))
                    },
                    discountContainer = {
                        CartPromoCode(
                            onCopyClick = {
                                onAction(
                                    HiddenDiscountAction.OnCopyPromoCode(
                                        HiddenDiscountViewModel.VALID_PROMO_CODE
                                    )
                                )
                            }
                        )
                    },
                    content = {
                        CartItem(
                            cartItem = cart,
                            promoCode = state.promoCode
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HiddenDiscountScreen(
        state = HiddenDiscountState(),
        onAction = {},
        snackbarHostState = SnackbarHostState()
    )
}
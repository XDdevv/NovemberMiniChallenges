package zed.rainxch.novemberminichallenges.long_press_compare

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.ic_scales
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.core.presentation.design_system.LongPressCompareColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.long_press_compare.components.CompareCartItem
import zed.rainxch.novemberminichallenges.long_press_compare.dialogs.LongPressComparison

@Composable
fun LongPressCompareRoot(
    viewModel: LongPressCompareViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LongPressCompareScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LongPressCompareScreen(
    state: LongPressCompareState,
    onAction: (LongPressCompareAction) -> Unit,
) {
    val windowSize = LocalWindowInfo.current
    val density = LocalDensity.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if (state.isShowingComparison) {
                        Text(
                            text = "Comparison",
                            fontWeight = FontWeight.SemiBold,
                            color = LongPressCompareColors.textPrimary,
                            fontFamily = hostGroteskFont(),
                            fontSize = 22.sp
                        )
                    } else {
                        if (state.selectedPhones.toList().any { it != null }) {
                            val selectedItemCount =
                                state.selectedPhones.toList().filter { it != null }.size
                            Text(
                                text = if (selectedItemCount > 1) {
                                    "$selectedItemCount items selected"
                                } else "$selectedItemCount item selected",
                                fontWeight = FontWeight.SemiBold,
                                color = LongPressCompareColors.textDisabled,
                                fontFamily = hostGroteskFont(),
                                fontSize = 16.sp
                            )
                        } else {
                            Text(
                                text = "TechStore",
                                fontWeight = FontWeight.SemiBold,
                                color = LongPressCompareColors.textPrimary,
                                fontFamily = hostGroteskFont(),
                                fontSize = 22.sp
                            )
                        }
                    }
                },
                navigationIcon = {
                    if (state.isShowingComparison) {
                        IconButton(
                            onClick = {
                                onAction(LongPressCompareAction.OnComparisonDialogClose)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    } else {
                        if (state.selectedPhones.toList().any { it != null }) {
                            IconButton(
                                onClick = {
                                    onAction(LongPressCompareAction.OnClearSelectionClick)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.List,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                },
                actions = {
                    if (!state.isShowingComparison) {
                        val selectedItemCount = state.selectedPhones
                            .toList()
                            .filter { phone ->
                                phone != null
                            }.size

                        if (state.selectedPhones.toList().any { it != null }) {
                            IconButton(
                                onClick = {
                                    onAction(LongPressCompareAction.OnCompareClick)
                                },
                                enabled = selectedItemCount > 1
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_scales),
                                    contentDescription = null,
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = null,
                                    tint = LongPressCompareColors.textPrimary
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LongPressCompareColors.bg
                )
            )
        },
        containerColor = if (state.isShowingComparison) {
            LongPressCompareColors.surface
        } else LongPressCompareColors.bg
    ) { innerPadding ->
        if (state.isShowingComparison) {
            LongPressComparison(
                phones = state.selectedPhones,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(8.dp)
            )
        } else {
            val widthDp = with(density) { windowSize.containerSize.width.toDp() }

            val gridCells = when {
                widthDp < 600.dp -> 2
                widthDp < 840.dp -> 3
                else -> 5
            }

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(8.dp),
                columns = GridCells.Fixed(gridCells),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.phones) { phone ->
                    CompareCartItem(
                        cartItem = phone,
                        onLongPress = {
                            onAction(LongPressCompareAction.OnPhoneLongClick(phone))
                        },
                        onTap = {
                            onAction(LongPressCompareAction.OnPhoneClick(phone))
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    LongPressCompareScreen(
        state = LongPressCompareState(),
        onAction = {}
    )
}
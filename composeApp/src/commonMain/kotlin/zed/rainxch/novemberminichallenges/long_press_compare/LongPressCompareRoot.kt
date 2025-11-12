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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.ic_scales
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.core.presentation.design_system.LongPressCompareColors
import zed.rainxch.novemberminichallenges.long_press_compare.components.CompareCartItem

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
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
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
                },
                title = {

                },
                actions = {
                    if (state.selectedPhones.toList().any { it != null }) {
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_scales),
                                contentDescription = null
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LongPressCompareColors.bg
                )
            )
        },
        containerColor = LongPressCompareColors.bg
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            columns = GridCells.Fixed(2),
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

@Preview
@Composable
private fun Preview() {
    LongPressCompareScreen(
        state = LongPressCompareState(),
        onAction = {}
    )
}
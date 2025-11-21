package zed.rainxch.novemberminichallenges.global_deals.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.changeLanguage
import zed.rainxch.novemberminichallenges.core.presentation.design_system.GlobalDealColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.global_deals.data.languagePreferences
import zed.rainxch.novemberminichallenges.global_deals.presentation.components.PopupLanguage
import zed.rainxch.novemberminichallenges.global_deals.presentation.components.ProductItem
import zed.rainxch.novemberminichallenges.global_deals.domain.model.Language
import zed.rainxch.novemberminichallenges.global_deals.presentation.models.image

@Composable
fun GlobalDealsRoot(
    viewModel: GlobalDealsViewModel = viewModel {
        GlobalDealsViewModel(languagePreferences())
    }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GlobalDealsScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalDealsScreen(
    state: GlobalDealsState,
    onAction: (GlobalDealsAction) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "SALE",
                        fontFamily = hostGroteskFont(),
                        fontWeight = FontWeight.SemiBold,
                        color = GlobalDealColors.textPrimary,
                        fontSize = 22.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            changeLanguage(Language.English)
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = GlobalDealColors.textPrimary,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {

                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = GlobalDealColors.textPrimary,
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ShoppingCart,
                                contentDescription = null
                            )
                        }
                        IconButton(
                            onClick = {
                                onAction(GlobalDealsAction.OnSelectedLanguageClick)
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = GlobalDealColors.textPrimary,
                            )
                        ) {
                            Image(
                                painter = painterResource(state.selectedLanguage.image()),
                                contentDescription = null
                            )
                        }
                    }

                    if (state.isLanguageDropdownVisible) {
                        Popup(
                            onDismissRequest = {
                                onAction(GlobalDealsAction.OnLanguagePopupClose)
                            },
                            offset = IntOffset(x = -50, y = 120)
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .background(GlobalDealColors.surface)
                                    .padding(vertical = 4.dp, horizontal = 12.dp)
                            ) {
                                items(state.popupLanguages) { language ->
                                    PopupLanguage(
                                        language = language,
                                        onClick = {
                                            changeLanguage(language)
                                            onAction(GlobalDealsAction.OnLanguageSelected(language))
                                        }
                                    )
                                }
                            }
                        }
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GlobalDealColors.bg
                )
            )
        },
        containerColor = GlobalDealColors.bg
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            items(state.products) { product ->
                ProductItem(
                    product = product
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    GlobalDealsScreen(
        state = GlobalDealsState(),
        onAction = {}
    )
}
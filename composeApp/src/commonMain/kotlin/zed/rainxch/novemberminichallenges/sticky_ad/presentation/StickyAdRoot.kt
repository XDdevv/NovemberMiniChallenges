package zed.rainxch.novemberminichallenges.sticky_ad.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.core.presentation.design_system.StickyAdColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.sticky_ad.presentation.components.StickyAdBanner
import zed.rainxch.novemberminichallenges.sticky_ad.presentation.components.StickyAdProductItem
import zed.rainxch.novemberminichallenges.sticky_ad.presentation.models.StickyAdList

@Composable
fun StickyAdRoot(
    viewModel: StickyAdViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    StickyAdScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StickyAdScreen(
    state: StickyAdState,
    onAction: (StickyAdAction) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "TechStore",
                        fontWeight = FontWeight.SemiBold,
                        color = StickyAdColors.textPrimary,
                        fontFamily = hostGroteskFont(),
                        fontSize = 22.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            tint = StickyAdColors.textPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = StickyAdColors.surface
                )
            )
        },
        containerColor = StickyAdColors.surface
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            state.items.forEach { item ->
                when (item) {
                    is StickyAdList.StickyAdDiscountBanner -> {
                        if (item.isVisible) {
                            stickyHeader(
                                key = item.title
                            ) {
                                StickyAdBanner(
                                    banner = item,
                                    onCloseClick = {
                                        onAction(StickyAdAction.OnBannerCloseClick(item))
                                    }
                                )
                            }
                        }
                    }

                    is StickyAdList.StickyAdProduct -> {
                        item(
                            key = item.title
                        ) {
                            StickyAdProductItem(
                                product = item
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    StickyAdScreen(
        state = StickyAdState(),
        onAction = {}
    )
}

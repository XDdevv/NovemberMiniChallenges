package zed.rainxch.novemberminichallenges.sticky_ad.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

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

@Composable
fun StickyAdScreen(
    state: StickyAdState,
    onAction: (StickyAdAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    StickyAdScreen(
        state = StickyAdState(),
        onAction = {}
    )
}
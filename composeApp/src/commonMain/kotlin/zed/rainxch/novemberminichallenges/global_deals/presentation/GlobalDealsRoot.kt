package zed.rainxch.novemberminichallenges.global_deals.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GlobalDealsRoot(
    viewModel: GlobalDealsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GlobalDealsScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun GlobalDealsScreen(
    state: GlobalDealsState,
    onAction: (GlobalDealsAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    GlobalDealsScreen(
        state = GlobalDealsState(),
        onAction = {}
    )
}
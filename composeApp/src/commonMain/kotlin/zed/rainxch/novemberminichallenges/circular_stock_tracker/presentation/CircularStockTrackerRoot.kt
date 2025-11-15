package zed.rainxch.novemberminichallenges.circular_stock_tracker.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CircularStockTrackerRoot(
    viewModel: CircularStockTrackerViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CircularStockTrackerScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun CircularStockTrackerScreen(
    state: CircularStockTrackerState,
    onAction: (CircularStockTrackerAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    CircularStockTrackerScreen(
        state = CircularStockTrackerState(),
        onAction = {}
    )
}
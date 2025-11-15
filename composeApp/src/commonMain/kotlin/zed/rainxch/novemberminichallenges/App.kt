package zed.rainxch.novemberminichallenges

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.circular_stock_tracker.presentation.CircularStockTrackerRoot
import zed.rainxch.novemberminichallenges.circular_stock_tracker.presentation.CircularStockTrackerViewModel

@Composable
@Preview
fun App() {
    CircularStockTrackerRoot(
        viewModel = CircularStockTrackerViewModel()
    )
}
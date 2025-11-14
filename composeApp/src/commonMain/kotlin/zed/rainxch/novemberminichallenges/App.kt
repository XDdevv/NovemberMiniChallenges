package zed.rainxch.novemberminichallenges

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.long_press_compare.LongPressCompareRoot
import zed.rainxch.novemberminichallenges.long_press_compare.LongPressCompareViewModel

@Composable
@Preview
fun App() {
    LongPressCompareRoot(
        viewModel = LongPressCompareViewModel()
    )
}
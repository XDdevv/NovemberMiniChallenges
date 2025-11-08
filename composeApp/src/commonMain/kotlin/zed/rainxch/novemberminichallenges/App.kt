package zed.rainxch.novemberminichallenges

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.hidden_discount.HiddenDiscountRoot
import zed.rainxch.novemberminichallenges.hidden_discount.HiddenDiscountViewModel

@Composable
@Preview
fun App() {
    HiddenDiscountRoot(
        viewModel = viewModel {
            HiddenDiscountViewModel()
        }
    )
}
package zed.rainxch.novemberminichallenges.hidden_discount

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HiddenDiscountRoot(
    viewModel: HiddenDiscountViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HiddenDiscountScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun HiddenDiscountScreen(
    state: HiddenDiscountState,
    onAction: (HiddenDiscountAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    HiddenDiscountScreen(
        state = HiddenDiscountState(),
        onAction = {}
    )
}
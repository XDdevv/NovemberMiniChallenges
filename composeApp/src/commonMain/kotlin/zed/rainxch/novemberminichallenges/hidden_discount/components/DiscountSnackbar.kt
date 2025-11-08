package zed.rainxch.novemberminichallenges.hidden_discount.components

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import zed.rainxch.novemberminichallenges.core.presentation.design_system.HiddenDiscountColors

@Composable
fun DiscountSnackbar(
    snackbarData: SnackbarData
) {
    Snackbar(
        snackbarData = snackbarData,
        containerColor = HiddenDiscountColors.snackbar,
        contentColor = HiddenDiscountColors.textAlt,
        shape = CutCornerShape(0.dp)
    )
}
package zed.rainxch.novemberminichallenges.global_deals.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import zed.rainxch.novemberminichallenges.core.presentation.design_system.GlobalDealColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.global_deals.domain.model.Language
import zed.rainxch.novemberminichallenges.global_deals.presentation.models.displayText
import zed.rainxch.novemberminichallenges.global_deals.presentation.models.image
import zed.rainxch.novemberminichallenges.global_deals.presentation.models.localeKey

@Composable
fun PopupLanguage(
    language: Language,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp)
    ) {
        Image(
            painter = painterResource(language.image()),
            contentDescription = language.localeKey(),
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = language.displayText(),
            fontFamily = hostGroteskFont(),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = GlobalDealColors.textPrimary
        )
    }
}
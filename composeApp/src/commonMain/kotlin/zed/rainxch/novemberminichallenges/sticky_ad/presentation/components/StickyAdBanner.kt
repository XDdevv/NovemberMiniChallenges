package zed.rainxch.novemberminichallenges.sticky_ad.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.sticky_ad_discount_bg
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.core.presentation.design_system.StickyAdColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.sticky_ad.presentation.models.StickyAdList

@Composable
fun StickyAdBanner(
    banner: StickyAdList.StickyAdDiscountBanner,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
    ) {
        Image(
            painter = painterResource(Res.drawable.sticky_ad_discount_bg),
            contentDescription = null,
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(top = 18.dp, start = 16.dp)
        ) {
            Text(
                text = banner.title,
                fontFamily = hostGroteskFont(),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = StickyAdColors.textOnDiscount
            )

            Text(
                text = banner.discount,
                fontFamily = hostGroteskFont(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 44.sp,
                color = StickyAdColors.textAlt
            )
        }

        IconButton(
            onClick = onCloseClick,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = StickyAdColors.textOnDiscount
            )
        }
    }
}

@Preview
@Composable
fun StickyAdBannerPreview() {
    StickyAdBanner(
        banner = StickyAdList.StickyAdDiscountBanner(
            title = "Black Friday Deals",
            discount = "-50%"
        ),
        onCloseClick = { }
    )
}
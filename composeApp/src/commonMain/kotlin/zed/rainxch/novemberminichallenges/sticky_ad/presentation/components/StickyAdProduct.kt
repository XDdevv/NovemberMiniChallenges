package zed.rainxch.novemberminichallenges.sticky_ad.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.sticky_ad_1
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.core.presentation.design_system.StickyAdColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.sticky_ad.presentation.models.StickyAdList

@Composable
fun StickyAdProductItem(
    product: StickyAdList.StickyAdProduct,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Image(
            painter = painterResource(product.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(182.dp)
                .background(
                    color = StickyAdColors.outline,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp),
            contentScale = ContentScale.Fit,
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = product.title,
            fontFamily = hostGroteskFont(),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = StickyAdColors.textSecondary
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = product.price,
            fontFamily = hostGroteskFont(),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = StickyAdColors.textPrimary
        )
    }
}

@Preview
@Composable
fun StickyAdProductItemPreview() {
    StickyAdProductItem(
        product = StickyAdList.StickyAdProduct(
            imageRes = Res.drawable.sticky_ad_1,
            title = "Google Pixel 9 Pro",
            price = "$999"
        )
    )
}
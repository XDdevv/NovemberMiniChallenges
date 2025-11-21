package zed.rainxch.novemberminichallenges.global_deals.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.product_1
import novemberminichallenges.composeapp.generated.resources.product_1_discount_price
import novemberminichallenges.composeapp.generated.resources.product_1_name
import novemberminichallenges.composeapp.generated.resources.product_1_original_price
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.core.presentation.design_system.GlobalDealColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.global_deals.presentation.models.Product

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .height(220.dp)
        ) {
            Image(
                painter = painterResource(product.productImage),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            lightColorScheme()
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(16.dp)
                    .align(Alignment.TopEnd),
                tint = GlobalDealColors.textDisabled,
            )

            Text(
                text = "-${product.discountPercent}%",
                color = GlobalDealColors.textAlt,
                fontFamily = hostGroteskFont(),
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                modifier = Modifier
                    .background(GlobalDealColors.discount)
                    .padding(vertical = 2.dp, horizontal = 8.dp)
                    .align(Alignment.BottomStart)
            )
        }

        Spacer(Modifier.height(6.dp))

        Text(
            text = stringResource(product.nameRes),
            color = GlobalDealColors.textPrimary,
            fontFamily = hostGroteskFont(),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        )

        Spacer(Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = stringResource(product.originalPriceRes),
                color = GlobalDealColors.discount,
                fontFamily = hostGroteskFont(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            )

            Text(
                text = stringResource(product.discountPriceRes),
                color = GlobalDealColors.textDisabled,
                fontFamily = hostGroteskFont(),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                textDecoration = TextDecoration.LineThrough
            )

        }
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    ProductItem(
        product = Product(
            id = 0,
            nameRes = Res.string.product_1_name,
            productImage = Res.drawable.product_1,
            originalPriceRes = Res.string.product_1_original_price,
            discountPriceRes = Res.string.product_1_discount_price,
            discountPercent = 56
        )
    )
}
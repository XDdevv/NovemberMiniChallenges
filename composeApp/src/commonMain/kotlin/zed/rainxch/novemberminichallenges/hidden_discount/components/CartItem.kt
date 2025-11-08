package zed.rainxch.novemberminichallenges.hidden_discount.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import novemberminichallenges.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import zed.rainxch.novemberminichallenges.core.presentation.design_system.HiddenDiscountColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.hidden_discount.model.CartItem
import zed.rainxch.novemberminichallenges.hidden_discount.model.PromoCode
import kotlin.math.round

@Composable
fun CartItem(
    cartItem: CartItem,
    promoCode: PromoCode? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(HiddenDiscountColors.surface)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(cartItem.imageRes),
            contentDescription = cartItem.name,
            modifier = Modifier.size(96.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = cartItem.name,
                fontFamily = hostGroteskFont(),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = HiddenDiscountColors.textPrimary
            )

            Spacer(Modifier.height(2.dp))

            Text(
                text = cartItem.shortDescription,
                fontFamily = hostGroteskFont(),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = HiddenDiscountColors.textDisabled
            )

            Spacer(Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = null,
                        tint = HiddenDiscountColors.textDisabled,
                        modifier = Modifier
                            .size(32.dp)
                            .drawBehind {
                                drawRect(
                                    style = Stroke(4f),
                                    color = HiddenDiscountColors.outline
                                )
                            }
                            .padding(4.dp)
                    )

                    Text(
                        text = "1",
                        fontFamily = hostGroteskFont(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = HiddenDiscountColors.textPrimary
                    )

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .drawBehind {
                                drawRect(
                                    style = Stroke(4f),
                                    color = HiddenDiscountColors.outline
                                )
                            }
                            .padding(4.dp),
                    )
                }


                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (promoCode != null) {
                        val discount = (cartItem.price * (100 - promoCode.discount) / 100)
                        val formatted = round(discount).toInt().toString()

                        Text(
                            text = "$${formatted}",
                            fontFamily = hostGroteskFont(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = HiddenDiscountColors.textPrimary
                        )

                        Text(
                            text = "$${cartItem.price}".removeSuffix(".0"),
                            fontFamily = hostGroteskFont(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.LineThrough,
                            color = HiddenDiscountColors.textDisabled
                        )
                    } else {
                        Text(
                            text = "$${cartItem.price}".removeSuffix(".0"),
                            fontFamily = hostGroteskFont(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = HiddenDiscountColors.textPrimary
                        )
                    }
                }
            }
        }
    }
}
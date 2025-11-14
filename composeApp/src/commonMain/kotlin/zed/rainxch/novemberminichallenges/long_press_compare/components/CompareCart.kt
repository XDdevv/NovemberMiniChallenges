package zed.rainxch.novemberminichallenges.long_press_compare.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.core.presentation.design_system.LongPressCompareColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.long_press_compare.models.LongPressPhone
import kotlin.math.round

@Composable
fun CompareCartItem(
    cartItem: LongPressPhone,
    onLongPress: () -> Unit,
    onTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        onLongPress()
                    },
                    onTap = {
                        onTap()
                    })
            }
            .dropShadow(
                shape = RoundedCornerShape(12.dp),
                shadow = Shadow(
                    radius = 4.dp,
                    spread = 0.dp,
                    offset = DpOffset(0.dp, 4.dp),
                    color = LongPressCompareColors.textPrimary.copy(alpha = .04f),
                )
            )
            .background(
                color = LongPressCompareColors.surface,
                shape = RoundedCornerShape(12.dp)
            )
            .then(
                if (cartItem.selected) {
                    Modifier.border(
                        width = 2.dp,
                        color = LongPressCompareColors.brand,
                        shape = RoundedCornerShape(12.dp)
                    )
                } else Modifier
            )
            .padding(8.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(cartItem.phone.imageRes),
                contentDescription = null,
                modifier = Modifier.size(170.dp),
                alignment = Alignment.Center
            )

            cartItem.phone.discount?.let { discount ->
                Text(
                    text = "-${discount.toInt()}%",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = LongPressCompareColors.textAlt,
                    modifier = Modifier
                        .padding(10.dp)
                        .background(
                            color = LongPressCompareColors.discount,
                            shape = CircleShape
                        )
                        .padding(vertical = 2.dp, horizontal = 6.dp)
                        .align(Alignment.TopEnd)
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        Text(
            text = cartItem.phone.modelName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = hostGroteskFont(),
            color = LongPressCompareColors.textSecondary
        )

        Spacer(Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            cartItem.phone.discount?.let { discount ->
                val discount = (cartItem.phone.price * (100 - discount) / 100)
                val formatted = round(discount).toInt().toString()

                Text(
                    text = "$$formatted",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = hostGroteskFont(),
                    color = LongPressCompareColors.discount
                )

                Spacer(Modifier.width(6.dp))
            }

            Text(
                text = "$${round(cartItem.phone.price).toInt()}",
                fontSize = if (cartItem.phone.discount == null) {
                    18.sp
                } else 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = hostGroteskFont(),
                color = if (cartItem.phone.discount == null) {
                    LongPressCompareColors.textPrimary
                } else LongPressCompareColors.textDisabled,
                textDecoration = if (cartItem.phone.discount == null) {
                    TextDecoration.None
                } else TextDecoration.LineThrough
            )
        }

        Spacer(Modifier.height(6.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = LongPressCompareColors.brand
            )
        ) {
            Text(
                text = "Buy",
                color = LongPressCompareColors.textAlt,
                fontFamily = hostGroteskFont(),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
fun CompareCartItemPreview(
    cartItem: LongPressPhone
) {
    CompareCartItem(
        cartItem = cartItem,
        onLongPress = { },
        onTap = { }
    )
}
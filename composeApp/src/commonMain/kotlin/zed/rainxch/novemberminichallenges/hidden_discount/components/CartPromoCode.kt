package zed.rainxch.novemberminichallenges.hidden_discount.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.novemberminichallenges.core.presentation.design_system.HiddenDiscountColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.hidden_discount.HiddenDiscountViewModel

@Composable
fun CartPromoCode(
    onCopyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xffD33F3F),
                            Color(0xff7C1414),
                        )
                    )
                )
                .drawWithContent {
                    drawContent()

                    drawCircle(
                        color = HiddenDiscountColors.bg,
                        radius = 50f,
                        center = Offset(y = -20f, x = size.width / 2 - 20f)
                    )

                    drawCircle(
                        color = HiddenDiscountColors.bg,
                        radius = 50f,
                        center = Offset(y = size.height + 20f, x = size.width / 2 - 20f)
                    )

                    (0..12).forEach { index ->
                        drawRect(
                            color = HiddenDiscountColors.outlineAlt,
                            size = Size(
                                width = 4f,
                                height = 36f
                            ),
                            topLeft = Offset(
                                x = size.width / 2 - 20f,
                                y = index * 50f
                            )
                        )
                    }
                }
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(.5f)
            ) {
                Text(
                    text = "BLACK FRIDAY SALE",
                    fontFamily = hostGroteskFont(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = HiddenDiscountColors.textOnDiscount
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = "25%",
                    fontFamily = hostGroteskFont(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 44.sp,
                    color = HiddenDiscountColors.textAlt,
                    lineHeight = 44.sp
                )
                Text(
                    text = "OFF",
                    fontFamily = hostGroteskFont(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = HiddenDiscountColors.textAlt
                )
            }

            Column(
                modifier = Modifier.weight(.5f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = HiddenDiscountViewModel.VALID_PROMO_CODE,
                        fontFamily = hostGroteskFont(),
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = HiddenDiscountColors.textAlt,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .height(40.dp)
                            .border(1.dp, HiddenDiscountColors.outlineAlt)
                            .padding(vertical = 6.dp, horizontal = 16.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(HiddenDiscountColors.surface)
                            .clickable {
                                onCopyClick()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = null,
                            tint = HiddenDiscountColors.discount,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        VerticalDivider()
    }
}
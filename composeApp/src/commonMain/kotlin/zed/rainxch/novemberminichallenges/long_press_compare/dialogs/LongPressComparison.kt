package zed.rainxch.novemberminichallenges.long_press_compare.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import zed.rainxch.novemberminichallenges.core.presentation.design_system.LongPressCompareColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont
import zed.rainxch.novemberminichallenges.long_press_compare.models.Phone
import kotlin.math.round

@Composable
fun LongPressComparison(
    phones: Pair<Phone?, Phone?>,
    modifier: Modifier = Modifier
) {
    if (phones.toList().all { it == null }) return

    Column(
        modifier = modifier
            .background(Color.White)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
        ) {
            phones.toList().forEach {
                ComparisonPhone(
                    phone = it!!,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        HorizontalDivider(
            color = LongPressCompareColors.outline
        )

        ComparisonTable(
            label = "Display",
            contentLeft = phones.first!!.display,
            contentRight = phones.second!!.display
        )

        HorizontalDivider(
            color = LongPressCompareColors.outline
        )

        ComparisonTable(
            label = "Main Camera",
            contentLeft = phones.first!!.mainCamera,
            contentRight = phones.second!!.mainCamera
        )

        ComparisonTable(
            label = "Front Camera",
            contentLeft = phones.first!!.frontCamera,
            contentRight = phones.second!!.frontCamera
        )

        HorizontalDivider(
            color = LongPressCompareColors.outline
        )

        ComparisonTable(
            label = "Processor",
            contentLeft = phones.first!!.processor,
            contentRight = phones.second!!.processor
        )

        ComparisonTable(
            label = "RAM",
            contentLeft = phones.first!!.ram,
            contentRight = phones.second!!.ram
        )

        ComparisonTable(
            label = "Storage",
            contentLeft = phones.first!!.storage,
            contentRight = phones.second!!.storage
        )

        HorizontalDivider(
            color = LongPressCompareColors.outline
        )

        ComparisonTable(
            label = "Battery (mAh)",
            contentLeft = phones.first!!.batteryMAH,
            contentRight = phones.second!!.batteryMAH
        )
    }
}

@Composable
private fun ComparisonTable(
    label: String,
    contentLeft: String,
    contentRight: String,
) {
    Column {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = hostGroteskFont(),
            color = LongPressCompareColors.textSecondary,
        )

        Spacer(Modifier.height(4.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = contentLeft,
                fontSize = 14.sp,
                fontFamily = hostGroteskFont(),
                fontWeight = FontWeight.Normal,
                color = LongPressCompareColors.textPrimary,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = contentRight,
                fontSize = 14.sp,
                fontFamily = hostGroteskFont(),
                fontWeight = FontWeight.Normal,
                color = LongPressCompareColors.textPrimary,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ComparisonPhone(
    phone: Phone,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box(
            Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = LongPressCompareColors.outline,
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Image(
                painter = painterResource(phone.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                    .heightIn(min = 184.dp)
            )

            phone.discount?.let { discount ->
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
            text = phone.modelName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = hostGroteskFont(),
            color = LongPressCompareColors.textSecondary
        )

        Spacer(Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            phone.discount?.let { discount ->
                val discount = (phone.price * (100 - discount) / 100)
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
                text = "$${round(phone.price).toInt()}",
                fontSize = if (phone.discount == null) {
                    18.sp
                } else 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = hostGroteskFont(),
                color = if (phone.discount == null) {
                    LongPressCompareColors.textPrimary
                } else LongPressCompareColors.textDisabled,
                textDecoration = if (phone.discount == null) {
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
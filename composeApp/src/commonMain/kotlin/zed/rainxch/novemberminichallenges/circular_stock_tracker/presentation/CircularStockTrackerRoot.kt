package zed.rainxch.novemberminichallenges.circular_stock_tracker.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import novemberminichallenges.composeapp.generated.resources.Res
import novemberminichallenges.composeapp.generated.resources.socks
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.novemberminichallenges.core.presentation.design_system.CircularStockTrackerColors
import zed.rainxch.novemberminichallenges.core.presentation.design_system.hostGroteskFont

@Composable
fun CircularStockTrackerRoot(
    viewModel: CircularStockTrackerViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CircularStockTrackerScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun CircularStockTrackerScreen(
    state: CircularStockTrackerState,
    onAction: (CircularStockTrackerAction) -> Unit,
) {
    Scaffold(
        containerColor = CircularStockTrackerColors.bg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val background: Brush = remember(state.remainingDiscountPrice) {
                if (state.remainingDiscountPrice >= 1) {
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xffD33F3F),
                            Color(0xff7C1414),
                        )
                    )
                } else SolidColor(CircularStockTrackerColors.outline)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(background),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.socks),
                    contentDescription = "Socks",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .width(380.dp)
                        .height(296.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-16).dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(CircularStockTrackerColors.surface)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Nike Air Zoom Pegasus 41",
                            fontFamily = hostGroteskFont(),
                            fontWeight = FontWeight.Medium,
                            color = CircularStockTrackerColors.textPrimary,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Legendary running shoes with Air Zoom technology and ReactX cushioning for daily training and marathons.",
                            fontFamily = hostGroteskFont(),
                            fontWeight = FontWeight.Normal,
                            color = CircularStockTrackerColors.textDisabled,
                            fontSize = 12.sp
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top)
                    ) {
                        Text(
                            text = "$100",
                            fontFamily = hostGroteskFont(),
                            fontWeight = FontWeight.Bold,
                            color = CircularStockTrackerColors.discount,
                            fontSize = 24.sp
                        )
                        Text(
                            text = "$160",
                            fontFamily = hostGroteskFont(),
                            fontWeight = FontWeight.Medium,
                            color = CircularStockTrackerColors.textDisabled,
                            fontSize = 14.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Choose your size",
                    fontFamily = hostGroteskFont(),
                    fontWeight = FontWeight.Normal,
                    color = CircularStockTrackerColors.textDisabled,
                    fontSize = 12.sp
                )

                Spacer(Modifier.height(4.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(6) { item ->
                        Text(
                            text = "${39 + item}",
                            fontFamily = hostGroteskFont(),
                            fontWeight = FontWeight.Medium,
                            color = if (39 + item == 42) {
                                CircularStockTrackerColors.textAlt
                            } else CircularStockTrackerColors.textSecondary,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .then(
                                    if (39 + item != 42) {
                                        Modifier.drawWithContent {
                                            drawContent()

                                            drawRoundRect(
                                                color = CircularStockTrackerColors.outline,
                                                cornerRadius = CornerRadius(10f),
                                                style = Stroke(width = 4f)
                                            )
                                        }
                                    } else Modifier
                                )
                                .then(
                                    if (39 + item == 42) {
                                        Modifier.background(
                                            color = CircularStockTrackerColors.textSecondary,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                    } else Modifier
                                )
                                .padding(vertical = 6.dp, horizontal = 16.dp)
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                HorizontalDivider(color = CircularStockTrackerColors.bg)

                Spacer(Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            progress = {
                                (state.remainingDiscountPrice) / 50f
                            },
                            color = CircularStockTrackerColors.discount,
                            trackColor = CircularStockTrackerColors.outline,
                            strokeCap = StrokeCap.Square,
                            gapSize = 0.dp
                        )

                        AnimatedContent(
                            targetState = state.remainingDiscountPrice,
                            transitionSpec = {
                                scaleIn() togetherWith scaleOut()
                            }
                        ) { value ->
                            Text(
                                text = value.toString(),
                                fontFamily = hostGroteskFont(),
                                fontWeight = FontWeight.Medium,
                                color = CircularStockTrackerColors.textSecondary,
                                fontSize = 13.sp,
                                maxLines = 1
                            )
                        }
                    }

                    Text(
                        text = "Remaining at discounted price",
                        fontFamily = hostGroteskFont(),
                        fontWeight = FontWeight.Normal,
                        color = CircularStockTrackerColors.textSecondary,
                        fontSize = 12.sp
                    )
                }

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {
                        onAction(CircularStockTrackerAction.OnBuyClick)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CircularStockTrackerColors.textPrimary,
                        disabledContainerColor = CircularStockTrackerColors.outline
                    ),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(vertical = 14.dp),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.remainingDiscountPrice >= 1
                ) {
                    Text(
                        text = if(state.remainingDiscountPrice >= 1) {
                            "Buy"
                        } else "Out if Stock",
                        fontFamily = hostGroteskFont(),
                        fontWeight = FontWeight.Medium,
                        color = CircularStockTrackerColors.textAlt,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CircularStockTrackerScreen(
        state = CircularStockTrackerState(remainingDiscountPrice = 10),
        onAction = {}
    )
}

@Preview
@Composable
private fun Preview2() {
    CircularStockTrackerScreen(
        state = CircularStockTrackerState(remainingDiscountPrice = 0),
        onAction = {}
    )
}
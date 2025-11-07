package zed.rainxch.novemberminichallenges.core.presentation.design_system

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import novemberminichallenges.composeapp.generated.resources.HostGrotesk_Bold
import novemberminichallenges.composeapp.generated.resources.HostGrotesk_Light
import novemberminichallenges.composeapp.generated.resources.HostGrotesk_Medium
import novemberminichallenges.composeapp.generated.resources.HostGrotesk_Regular
import novemberminichallenges.composeapp.generated.resources.HostGrotesk_SemiBold
import novemberminichallenges.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun hostGroteskFont(): FontFamily = FontFamily(
    Font(Res.font.HostGrotesk_Light, FontWeight.Light),
    Font(Res.font.HostGrotesk_Regular, FontWeight.Normal),
    Font(Res.font.HostGrotesk_Medium, FontWeight.Medium),
    Font(Res.font.HostGrotesk_SemiBold, FontWeight.SemiBold),
    Font(Res.font.HostGrotesk_Bold, FontWeight.Bold),
)
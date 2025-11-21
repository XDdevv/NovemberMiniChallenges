package zed.rainxch.novemberminichallenges

import androidx.compose.runtime.Composable
import zed.rainxch.novemberminichallenges.core.presentation.utils.ClipboardHelper
import zed.rainxch.novemberminichallenges.global_deals.domain.model.Language

@Composable
expect fun rememberClipboardHelper(): ClipboardHelper

expect fun changeLanguage(language: Language)
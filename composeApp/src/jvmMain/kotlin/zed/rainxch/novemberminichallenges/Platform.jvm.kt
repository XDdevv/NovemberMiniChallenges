package zed.rainxch.novemberminichallenges

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import zed.rainxch.novemberminichallenges.core.presentation.utils.ClipboardHelper
import zed.rainxch.novemberminichallenges.core.presentation.utils.JvmClipboardHelper
import zed.rainxch.novemberminichallenges.global_deals.domain.model.Language

@Composable
actual fun rememberClipboardHelper(): ClipboardHelper {
    return remember {
        JvmClipboardHelper()
    }
}

actual fun changeLanguage(language: Language) {
}
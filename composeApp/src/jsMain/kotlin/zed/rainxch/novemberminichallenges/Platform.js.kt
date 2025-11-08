package zed.rainxch.novemberminichallenges

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import zed.rainxch.novemberminichallenges.core.presentation.utils.ClipboardHelper
import zed.rainxch.novemberminichallenges.core.presentation.utils.JsClipboardHelper

@Composable
actual fun rememberClipboardHelper(): ClipboardHelper {
    return remember {
        JsClipboardHelper()
    }
}
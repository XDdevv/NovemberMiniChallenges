package zed.rainxch.novemberminichallenges

import android.content.ClipData
import android.content.ClipboardManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import zed.rainxch.novemberminichallenges.core.presentation.utils.ClipboardHelper

@Composable
actual fun rememberClipboardHelper(): ClipboardHelper {
    val context = LocalContext.current
    val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as ClipboardManager

    return remember {
        object : ClipboardHelper {
            override fun copy(text: String) {
                clipboard.setPrimaryClip(ClipData.newPlainText("", text))
            }
        }
    }
}
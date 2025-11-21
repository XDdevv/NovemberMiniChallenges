package zed.rainxch.novemberminichallenges

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import zed.rainxch.novemberminichallenges.core.presentation.utils.ClipboardHelper
import zed.rainxch.novemberminichallenges.global_deals.presentation.models.Language
import java.util.Locale
import androidx.core.content.edit

@Composable
actual fun rememberClipboardHelper(): ClipboardHelper {
    val context = LocalContext.current
    val clipboard =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    return remember {
        object : ClipboardHelper {
            override fun copy(text: String) {
                clipboard.setPrimaryClip(ClipData.newPlainText("", text))
            }
        }
    }
}

actual fun changeLanguage(language: Language) {
    val context = ContextProvider.context!!
    context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        .edit {
            putString("language", language.localeKey())
        }
    (context as? Activity)?.recreate()
}

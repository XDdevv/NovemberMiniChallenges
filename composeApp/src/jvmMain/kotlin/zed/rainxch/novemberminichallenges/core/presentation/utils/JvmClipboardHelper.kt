package zed.rainxch.novemberminichallenges.core.presentation.utils

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

class JvmClipboardHelper : ClipboardHelper {
    override fun copy(text: String) {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val selection = StringSelection(text)
        clipboard.setContents(selection, selection)
    }
}
package zed.rainxch.novemberminichallenges.core.presentation.utils

import kotlinx.browser.window

class JsClipboardHelper : ClipboardHelper {
    override fun copy(text: String) {
        if (js("typeof navigator !== 'undefined' && navigator.clipboard") as Boolean) {
            window.navigator.clipboard.writeText(text)
        } else {
            // Fallback for older browsers
            val textArea = kotlinx.browser.document.createElement("textarea")
            textArea.setAttribute("style", "position: fixed; top: -9999px; left: -9999px;")
            textArea.textContent = text
            kotlinx.browser.document.body?.appendChild(textArea)

            (textArea as? org.w3c.dom.HTMLTextAreaElement)?.select()
            kotlinx.browser.document.execCommand("copy")

            kotlinx.browser.document.body?.removeChild(textArea)
        }
    }
}
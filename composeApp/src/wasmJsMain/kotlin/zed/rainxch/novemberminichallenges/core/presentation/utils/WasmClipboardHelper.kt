package zed.rainxch.novemberminichallenges.core.presentation.utils

class WasmClipboardHelper : ClipboardHelper {
    override fun copy(text: String) {
        copyToClipboard(text)
    }
}

@OptIn(ExperimentalWasmJsInterop::class)
private fun copyToClipboard(text: String) {
    js("navigator.clipboard.writeText(text)")
}
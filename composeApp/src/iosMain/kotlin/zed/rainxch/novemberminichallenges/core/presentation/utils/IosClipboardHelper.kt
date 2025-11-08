package zed.rainxch.novemberminichallenges.core.presentation.utils

import platform.UIKit.UIPasteboard

class IosClipboardHelper : ClipboardHelper {
    override fun copy(text: String) {
        UIPasteboard.generalPasteboard.string = text
    }
}
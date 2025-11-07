package zed.rainxch.novemberminichallenges

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
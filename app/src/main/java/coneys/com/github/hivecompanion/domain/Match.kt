package coneys.com.github.hivecompanion.domain

data class Match(val player1: User, val player2: User,
                 val player1Points: Long, val player2Points: Long)
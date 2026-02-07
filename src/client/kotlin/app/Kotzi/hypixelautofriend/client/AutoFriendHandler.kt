package app.Kotzi.hypixelautofriend.client

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import java.util.regex.Pattern

/**
 * Automatically accepts Hypixel friend requests by detecting the friend request message
 * and sending the /friend &lt;name&gt; command.
 *
 * Logic adapted from Hytils Reborn (AutoFriend), originally from AutoFriend under MIT.
 * Pattern: "Friend request from &lt;name&gt;[ACCEPT] - [DENY] - [BLOCK]"
 */
object AutoFriendHandler {

    /**
     * Hypixel friend request message pattern (English).
     * Captures the sender's name which may include rank prefix e.g. [MVP+] PlayerName
     */
    private val FRIEND_REQUEST_PATTERN = Pattern.compile(
        "Friend request from (?<name>.+)\\[ACCEPT] - \\[DENY] - \\[BLOCK].*"
    )

    fun register() {
        ClientReceiveMessageEvents.ALLOW_GAME.register(::onGameMessage)
    }

    private fun onGameMessage(message: Text, overlay: Boolean): Boolean {
        if (!HypixelAutoFriendConfig.autoFriendEnabled) return true
        if (HypixelAutoFriendConfig.onlyOnHypixel && !isOnHypixel()) return true
        if (overlay) return true

        val plainText = message.getString().replace("\n", "")
        if (plainText.contains(": ")) return true

        val matcher = FRIEND_REQUEST_PATTERN.matcher(plainText)
        if (!matcher.find()) return true

        var name = matcher.group("name")?.trim() ?: return true
        if (name.startsWith("[")) {
            name = name.substring(name.indexOf("] ") + 2).trim()
        }

        sendFriendAccept(name)
        return !HypixelAutoFriendConfig.hideFriendRequestMessage
    }

    private fun sendFriendAccept(playerName: String) {
        val client = MinecraftClient.getInstance()
        val player = client.player ?: return
        val connection = player.networkHandler ?: return

        connection.sendChatCommand("friend $playerName")
    }

    private fun isOnHypixel(): Boolean {
        val client = MinecraftClient.getInstance()
        val address = client.currentServerEntry?.address ?: return false
        return address.contains("hypixel", ignoreCase = true)
    }
}

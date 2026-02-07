package app.Kotzi.hypixelautofriend.client

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.text.Text

object AutoFriendCommand {

    fun register() {
        ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
            registerCommands(dispatcher)
        }
    }

    private fun registerCommands(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(
            ClientCommandManager.literal("autofriend")
                .executes(::toggleAutoFriend)
        )
    }

    private fun toggleAutoFriend(context: CommandContext<FabricClientCommandSource>): Int {
        HypixelAutoFriendConfig.autoFriendEnabled = !HypixelAutoFriendConfig.autoFriendEnabled
        val enabled = HypixelAutoFriendConfig.autoFriendEnabled
        context.source.sendFeedback(
            Text.literal("Auto Friend: ${if (enabled) "\u00a7aON" else "\u00a7cOFF"}")
        )
        return 1
    }
}

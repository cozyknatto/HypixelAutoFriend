package app.Kotzi.hypixelautofriend.client

import net.fabricmc.api.ClientModInitializer

class HypixelautofriendClient : ClientModInitializer {

    override fun onInitializeClient() {
        AutoFriendHandler.register()
        AutoFriendCommand.register()
    }
}

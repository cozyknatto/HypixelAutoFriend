package app.Kotzi.hypixelautofriend.client

import net.fabricmc.api.ClientModInitializer

class HypixelautofriendClient : ClientModInitializer {

    override fun onInitializeClient() {
        HypixelAutoFriendConfig.load()
        AutoFriendHandler.register()
        AutoFriendCommand.register()
    }
}

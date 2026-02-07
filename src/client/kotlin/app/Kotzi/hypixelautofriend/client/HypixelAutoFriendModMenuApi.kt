package app.Kotzi.hypixelautofriend.client

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi

class HypixelAutoFriendModMenuApi : ModMenuApi {

    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { parent -> HypixelAutoFriendConfigScreen.create(parent) }
    }
}

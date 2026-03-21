package app.Kotzi.hypixelautofriend.client

import com.google.gson.Gson
import net.fabricmc.loader.api.FabricLoader

object HypixelAutoFriendConfig {

    var autoFriendEnabled: Boolean = true
    var hideFriendRequestMessage: Boolean = true
    var onlyOnHypixel: Boolean = true

    private val configFile = FabricLoader.getInstance().configDir
        .resolve("hypixelautofriend.json").toFile()
    private val gson = Gson()

    private data class ConfigData(
        val autoFriendEnabled: Boolean = true,
        val hideFriendRequestMessage: Boolean = true,
        val onlyOnHypixel: Boolean = true
    )

    fun load() {
        if (!configFile.exists()) return
        try {
            val data = gson.fromJson(configFile.readText(), ConfigData::class.java)
            autoFriendEnabled = data.autoFriendEnabled
            hideFriendRequestMessage = data.hideFriendRequestMessage
            onlyOnHypixel = data.onlyOnHypixel
        } catch (_: Exception) {
            // keep defaults if file is corrupt
        }
    }

    fun save() {
        configFile.writeText(
            gson.toJson(ConfigData(autoFriendEnabled, hideFriendRequestMessage, onlyOnHypixel))
        )
    }
}

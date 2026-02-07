package app.Kotzi.hypixelautofriend.client

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.CheckboxWidget
import net.minecraft.text.Text

class HypixelAutoFriendConfigScreen(parent: Screen?) : Screen(Text.literal("Hypixel Auto Friend")) {

    companion object {
        fun create(parent: Screen?): Screen = HypixelAutoFriendConfigScreen(parent)
    }

    private val parentScreen: Screen? = parent

    override fun init() {
        super.init()
        val centerX = width / 2
        val startY = height / 4

        // Auto Friend Enabled
        addDrawableChild(
            CheckboxWidget.builder(
                Text.literal("Automatically accept friend requests"),
                textRenderer
            )
                .pos(centerX - 155, startY)
                .checked(HypixelAutoFriendConfig.autoFriendEnabled)
                .callback { _, checked ->
                    HypixelAutoFriendConfig.autoFriendEnabled = checked
                }
                .build()
        )

        // Hide Friend Request Message
        addDrawableChild(
            CheckboxWidget.builder(
                Text.literal("Hide friend request message in chat"),
                textRenderer
            )
                .pos(centerX - 155, startY + 24)
                .checked(HypixelAutoFriendConfig.hideFriendRequestMessage)
                .callback { _, checked ->
                    HypixelAutoFriendConfig.hideFriendRequestMessage = checked
                }
                .build()
        )

        // Only on Hypixel
        addDrawableChild(
            CheckboxWidget.builder(
                Text.literal("Only when connected to Hypixel (recommended)"),
                textRenderer
            )
                .pos(centerX - 155, startY + 48)
                .checked(HypixelAutoFriendConfig.onlyOnHypixel)
                .callback { _, checked ->
                    HypixelAutoFriendConfig.onlyOnHypixel = checked
                }
                .build()
        )

        // Done button
        addDrawableChild(
            ButtonWidget.builder(Text.literal("Done")) {
                client?.setScreen(parentScreen)
            }.dimensions(centerX - 100, startY + 84, 200, 20).build()
        )
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        // Don't use renderBackground() - Mod Menu already blurred; "Can only blur once per frame" in 1.21
        context.fill(0, 0, width, height, 0xC0_10_10_10.toInt())
        super.render(context, mouseX, mouseY, delta)
        context.drawCenteredTextWithShadow(textRenderer, title, width / 2, 20, 0xFFFFFF)
    }

    override fun close() {
        client?.setScreen(parentScreen)
    }
}

package app.Kotzi.hypixelautofriend.client

/**
 * Client-side config for Hypixel Auto Friend.
 * Can be replaced with a proper config screen (e.g. ModMenu) later.
 */
object HypixelAutoFriendConfig {

    /** When true, automatically accept incoming Hypixel friend requests. */
    var autoFriendEnabled: Boolean = true

    /** When true, the friend request message is not shown in chat after auto-accepting. */
    var hideFriendRequestMessage: Boolean = true

    /** When true, auto-accept only runs when connected to Hypixel (recommended). */
    var onlyOnHypixel: Boolean = true
}

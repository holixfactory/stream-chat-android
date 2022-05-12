package io.getstream.chat.android.offline.event

import io.getstream.chat.android.client.events.ChatEvent

internal class DefaultEventHandlerFilter : EventHandlerFilter {
    override fun invoke(event: ChatEvent, currentUserId: String): Boolean {
        return true
    }
}

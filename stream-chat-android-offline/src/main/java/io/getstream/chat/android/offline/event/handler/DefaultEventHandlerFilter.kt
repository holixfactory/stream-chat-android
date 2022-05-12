package io.getstream.chat.android.offline.event.handler

import io.getstream.chat.android.client.events.ChatEvent
import io.getstream.chat.android.client.models.User

public class DefaultEventHandlerFilter : EventHandlerFilter {
    override fun invoke(event: ChatEvent, user: User): Boolean {
        return true
    }
}

package io.getstream.chat.android.offline.event.handler

import io.getstream.chat.android.client.events.ChatEvent
import io.getstream.chat.android.client.models.User

public interface EventHandlerFilter {
    public operator fun invoke(event: ChatEvent, user: User): Boolean
}

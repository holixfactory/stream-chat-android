package io.getstream.chat.android.offline.event

import io.getstream.chat.android.client.events.ChatEvent

public interface EventHandlerFilter {
    public operator fun invoke(event: ChatEvent, currentUserId: String): Boolean
}

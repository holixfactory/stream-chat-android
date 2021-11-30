package io.getstream.chat.android.ui.message.list.adapter.viewholder.attachment

import android.view.ViewGroup
import com.getstream.sdk.chat.adapter.MessageListItem
import io.getstream.chat.android.ui.common.extensions.internal.isMedia
import io.getstream.chat.android.ui.common.extensions.internal.streamThemeInflater
import io.getstream.chat.android.ui.databinding.StreamUiItemFileAttachmentGroupBinding
import io.getstream.chat.android.ui.databinding.StreamUiItemImageAttachmentBinding
import io.getstream.chat.android.ui.message.list.MessageListView
import io.getstream.chat.android.ui.message.list.adapter.MessageListListenerContainer

/**
 * Factory for creating the attachment contents displayed within message items.
 *
 * Displays media and file attachments by default.
 */
public open class AttachmentViewHolderFactory {

    /**
     * Listeners set on [MessageListView] that should be invoked when the user interacts with
     * list items.
     */
    protected var listenerContainer: MessageListListenerContainer? = null
        private set

    internal fun setListenerContainer(listenerContainer: MessageListListenerContainer?) {
        this.listenerContainer = listenerContainer
    }

    public companion object {
        private const val BUILT_IN_TYPE_OFFSET = 9000

        /**
         * The media attachment type.
         */
        public const val MEDIA: Int = BUILT_IN_TYPE_OFFSET + 1

        /**
         * The file attachment type.
         */
        public const val FILE: Int = BUILT_IN_TYPE_OFFSET + 2
    }

    /**
     * Determine the view type to be used for the given list of attachments.
     *
     * Supports media and file attachments by default. Make sure to call into the
     * super implementation when overriding this method if you want these default
     * attachment types to be handled.
     */
    public open fun getItemViewType(messageItem: MessageListItem.MessageItem): Int {
        // TODO update the logic for rendering attachments as files - match Compose implementation?
        val attachments = messageItem.message.attachments
        return when {
            attachments.isMedia() -> MEDIA
            else -> FILE
        }
    }

    /**
     * Create a ViewHolder for the given [viewType].
     *
     * Supports media and file attachments by default. Make sure to call into the
     * super implementation when overriding this method if you want these default
     * attachment types to be handled.
     */
    public open fun createViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AttachmentViewHolder {
        return when (viewType) {
            MEDIA -> {
                StreamUiItemImageAttachmentBinding
                    .inflate(parent.streamThemeInflater, parent, false)
                    .let { binding ->
                        MediaAttachmentsViewHolder(
                            binding = binding,
                            container = listenerContainer,
                        )
                    }
            }

            FILE -> {
                StreamUiItemFileAttachmentGroupBinding
                    .inflate(parent.streamThemeInflater, parent, false)
                    .let { binding ->
                        FileAttachmentsViewHolder(
                            binding = binding,
                            container = listenerContainer
                        )
                    }
            }

            else -> error("This view type: $viewType is not supported")
        }
    }
}

package volio.tech.sharefile.framework.presentation.send.image.smooth.viewdata

import androidx.recyclerview.widget.DiffUtil

interface ImageViewData {

    val itemId: String

    val layoutRes: Int

    fun areItemsTheSame(item: ImageViewData): Boolean

    fun areContentsTheSame(item: ImageViewData): Boolean

    fun shallowCopy(): ImageViewData

    object DiffCallback : DiffUtil.ItemCallback<ImageViewData>() {
        override fun areItemsTheSame(
            oldItem: ImageViewData,
            newItem: ImageViewData
        ): Boolean {
            return oldItem.areItemsTheSame(newItem)
        }

        override fun areContentsTheSame(
            oldItem: ImageViewData,
            newItem: ImageViewData
        ): Boolean {
            return oldItem.areContentsTheSame(newItem)
        }
    }

}
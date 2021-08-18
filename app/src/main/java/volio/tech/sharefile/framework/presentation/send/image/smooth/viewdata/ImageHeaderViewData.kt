package volio.tech.sharefile.framework.presentation.send.image.smooth.viewdata

import volio.tech.sharefile.R

data class ImageHeaderViewData(
    override val itemId: String,
    val title: String
) : ImageViewData {
    override val layoutRes: Int
        get() = R.layout.item_image_header

    override fun areItemsTheSame(item: ImageViewData): Boolean {
        return if (item !is ImageHeaderViewData) false
        else item.itemId == itemId
    }

    override fun areContentsTheSame(item: ImageViewData): Boolean {
        return if (item !is ImageHeaderViewData) false
        else item.title == title
    }

    override fun shallowCopy(): ImageViewData {
        return copy()
    }
}
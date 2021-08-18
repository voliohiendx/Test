package volio.tech.sharefile.framework.presentation.send.image.smooth.viewdata

import volio.tech.sharefile.R
import volio.tech.sharefile.business.domain.FileModel

data class ImageRowViewData(
    override val itemId: String,
    val images: List<FileModel>
) : ImageViewData {

    override val layoutRes: Int
        get() = R.layout.item_image_row

    override fun areItemsTheSame(item: ImageViewData): Boolean {
        return if (item !is ImageHeaderViewData) false
        else item.itemId == itemId
    }

    override fun areContentsTheSame(item: ImageViewData): Boolean {
        return false
    }

    override fun shallowCopy(): ImageViewData {
        return ImageRowViewData(itemId, ArrayList(images))
    }

}
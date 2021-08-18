package volio.tech.sharefile.framework.presentation.send.image.adapter

import androidx.recyclerview.widget.DiffUtil
import volio.tech.sharefile.business.domain.FileModel

class FileDiffUtilCallback : DiffUtil.ItemCallback<FileModel>() {
    override fun areItemsTheSame(oldItem: FileModel, newItem: FileModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FileModel, newItem: FileModel): Boolean {
        return oldItem == newItem
    }
}

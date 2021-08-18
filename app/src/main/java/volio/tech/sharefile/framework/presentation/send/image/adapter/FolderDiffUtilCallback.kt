package volio.tech.sharefile.framework.presentation.send.image.adapter

import androidx.recyclerview.widget.DiffUtil
import volio.tech.sharefile.business.domain.DateSelect
import volio.tech.sharefile.business.domain.Folder

class FolderDiffUtilCallback : DiffUtil.ItemCallback<Folder>() {
    override fun areItemsTheSame(oldItem: Folder, newItem: Folder): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Folder, newItem: Folder): Boolean {
        return false
    }
}

package volio.tech.sharefile.framework.presentation.send.image.adapter

import androidx.recyclerview.widget.DiffUtil
import volio.tech.sharefile.business.domain.DateSelect

class DateDiffUtilCallback : DiffUtil.ItemCallback<DateSelect>() {
    override fun areItemsTheSame(oldItem: DateSelect, newItem: DateSelect): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: DateSelect, newItem: DateSelect): Boolean {
        return false
    }
}

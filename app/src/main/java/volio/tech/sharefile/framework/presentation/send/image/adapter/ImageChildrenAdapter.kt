package volio.tech.sharefile.framework.presentation.send.image.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import volio.tech.sharefile.R
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.databinding.ItemChildrenImageBinding
import volio.tech.sharefile.util.DeviceDimensionsHelper.getDisplayWidth

class ImageChildrenAdapter(
) : ListAdapter<FileModel, RecyclerView.ViewHolder>(FileDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_children_image, parent, false)

        val desiredWidth = parent.context.getDisplayWidth() / 4

        return ItemChildren(view, desiredWidth.toInt())
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemChildren -> {
                holder.bind(getItem(position), false)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads.any { it is InfoMessageChanged }) {
                (holder as ItemChildren).bind(getItem(position), true)
            }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        (holder as ItemChildren).onViewRecycled()
    }

    class ItemChildren(
        view: View, private val desiredWidth: Int
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemChildrenImageBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(item: FileModel?, isUpdate: Boolean) {
            Glide.with(itemView.context)
                .load(item?.path)
                .override(desiredWidth, desiredWidth)
                .into(binding.imvItem)
        }

        fun onViewRecycled() {
            Glide.with(itemView.context).clear(binding.imvItem)
        }
    }

    class InfoMessageChanged

    companion object {

    }

}
package volio.tech.sharefile.framework.presentation.send.image.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import volio.tech.sharefile.R
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.databinding.ItemChildrenImageBinding

class ImageChildrenAdapter(
) : ListAdapter<FileModel, RecyclerView.ViewHolder>(FileDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_children_image, parent, false)

        return ItemChildren(view)
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
        Glide.with(holder.itemView.context)
            .clear(holder.itemView.findViewById<ImageView>(R.id.imvItem))
    }

    inner class ItemChildren(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemChildrenImageBinding.bind(itemView)

        init {
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: FileModel?, isUpdate: Boolean) {
            //  CoroutineScope(Dispatchers.Main).launch {
            //   Glide.with(itemView.context).load(item.path) .diskCacheStrategy(DiskCacheStrategy.ALL).into(binding.imvItem)
            //  binding.imvItem.setImageURI(item.path.toUri())
            //   }
            Log.d("Hienoc0818", position.toString())
       //     Glide.with(itemView.context).load(item?.path).override(300, 300).into(binding.imvItem)
        }
    }

    class InfoMessageChanged

    companion object {

    }

}
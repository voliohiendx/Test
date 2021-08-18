package volio.tech.sharefile.framework.presentation.send.image.smooth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import volio.tech.sharefile.R
import volio.tech.sharefile.databinding.ItemImageHeaderBinding
import volio.tech.sharefile.databinding.ItemImageRowBinding
import volio.tech.sharefile.framework.presentation.send.image.adapter.ImageChildrenAdapter
import volio.tech.sharefile.framework.presentation.send.image.smooth.viewdata.ImageHeaderViewData
import volio.tech.sharefile.framework.presentation.send.image.smooth.viewdata.ImageRowViewData
import volio.tech.sharefile.framework.presentation.send.image.smooth.viewdata.ImageViewData
import volio.tech.sharefile.util.gone
import volio.tech.sharefile.util.show
import java.text.SimpleDateFormat
import java.util.*

class SmoothImageAdapter(
    private val listener: SmoothCallback
) : ListAdapter<ImageViewData, RecyclerView.ViewHolder>(ImageViewData.DiffCallback) {

    private val sharedViewPool = RecyclerView.RecycledViewPool()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_image_row -> {
                ItemImageRowHolder(
                    ItemImageRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), sharedViewPool
                )
            }
            R.layout.item_image_header -> {
                ItemImageHeaderHolder(
                    ItemImageHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), listener
                )
            }
            else -> throw IllegalStateException("viewType not found")
        }
    }

    override fun getItemId(position: Int): Long {
        return AdapterIdUtils.hashString64Bit(getItem(position).itemId)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).layoutRes
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemImageRowHolder -> {
                holder.bind(getItem(position) as ImageRowViewData)
            }
            is ItemImageHeaderHolder -> {
                holder.bind(getItem(position) as ImageHeaderViewData)
            }
        }

    }

    private class ItemImageRowHolder(
        private val binding: ItemImageRowBinding,
        private val sharedViewPool: RecyclerView.RecycledViewPool
    ) : RecyclerView.ViewHolder(binding.root) {

        val childAdapter = ImageChildrenAdapter()

        init {
            binding.rvImages.apply {
                itemAnimator = null
                layoutManager = GridLayoutManager(binding.root.context, 4)
                setRecycledViewPool(sharedViewPool)
            }
            binding.rvImages.adapter = childAdapter
        }

        fun bind(item: ImageRowViewData) = with(binding) {
            if (item.isShow) {
                childAdapter.submitList(item.images)
                rvImages.show()
            } else {
                childAdapter.submitList(emptyList())
                rvImages.gone()
            }
        }

    }

    private class ItemImageHeaderHolder(
        private val binding: ItemImageHeaderBinding,
        private val listener: SmoothCallback
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ImageHeaderViewData) = with(binding) {
            tvName.text = item.title
            tvName.setOnClickListener {
                listener.onHeaderSelected(item)
            }
        }

    }

    interface SmoothCallback {
        fun onHeaderSelected(item: ImageHeaderViewData)
    }

}

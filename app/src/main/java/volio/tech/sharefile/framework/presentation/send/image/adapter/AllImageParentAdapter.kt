package volio.tech.sharefile.framework.presentation.send.image.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import volio.tech.sharefile.R
import volio.tech.sharefile.business.domain.DataLocal
import volio.tech.sharefile.business.domain.DateSelect
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.databinding.ItemImageParentBinding
import volio.tech.sharefile.util.gone
import volio.tech.sharefile.util.setPreventDoubleClick
import volio.tech.sharefile.util.show
import java.text.SimpleDateFormat

class AllImageParentAdapter(
    val dataLocal: DataLocal,
) : ListAdapter<DateSelect, RecyclerView.ViewHolder>(DateDiffUtilCallback()) {

    val formatter = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_parent, parent, false)

        return ItemParent(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemParent -> {
                holder.bind(currentList[position], false)
            }
        }
    }

    inner class ItemParent(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemImageParentBinding.bind(itemView)

        private var imageChildrenAdapter: ImageChildrenAdapter? = null
        var filteredList = mutableListOf<FileModel>()

        init {
            initRv(itemView)
        }

        fun bind(item: DateSelect, isUpdate: Boolean) {
            when (item.date) {
                formatter.format(System.currentTimeMillis()) -> {
                    binding.tvName.text = itemView.context.getString(R.string.today)
                }
                formatter.format(System.currentTimeMillis() - 86400000) -> {
                    binding.tvName.text = itemView.context.getString(R.string.yesterday)
                }
                else -> binding.tvName.text = item.date
            }

            filteredList = dataLocal.file.filter { it.dateCreated == item.date }.toMutableList()
            imageChildrenAdapter?.submitList(filteredList)

            if (!item.showFull) {
                binding.rvFullItem.gone()
            } else {
                binding.rvFullItem.show()
            }
            listener(item)
        }

        private fun listener(item: DateSelect) {
            itemView.setPreventDoubleClick {
                if (binding.rvFullItem.visibility == View.VISIBLE) {
                    binding.rvFullItem.gone()
                   // itemView.gone()
                    item.showFull = false
                } else {
                    binding.rvFullItem.show()
                    item.showFull = true
                }
            }
        }


        private fun initRv(itemView: View) {
            imageChildrenAdapter =
                ImageChildrenAdapter()

            binding.rvFullItem.apply {
                layoutManager = GridLayoutManager(itemView.context, 4)
            }
            binding.rvFullItem.adapter = imageChildrenAdapter

//            itemView.setOnClickListener {
//                binding.rvChildren.visibility= View.VISIBLE
//            }
        }
    }
}
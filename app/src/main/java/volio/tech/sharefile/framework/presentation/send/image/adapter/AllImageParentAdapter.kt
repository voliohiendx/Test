package volio.tech.sharefile.framework.presentation.send.image.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import volio.tech.sharefile.R
import volio.tech.sharefile.business.domain.DataLocal
import volio.tech.sharefile.business.domain.DateSelect
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.databinding.ItemImageParentBinding
import volio.tech.sharefile.util.gone
import volio.tech.sharefile.util.setPreventDoubleClick
import volio.tech.sharefile.util.show
import java.lang.Exception
import java.text.SimpleDateFormat

class AllImageParentAdapter(
    private val dataLocal: DataLocal,
) : ListAdapter<DateSelect, RecyclerView.ViewHolder>(DateDiffUtilCallback()) {

    private val formatter = SimpleDateFormat("dd/MM/yyyy")
    private val sharedViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemImageParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemParent(binding, formatter, dataLocal, sharedViewPool)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemParent -> {
                holder.bind(currentList[position], false)
            }
        }
    }

    class ItemParent(
        private val binding: ItemImageParentBinding,
        private val formatter: SimpleDateFormat,
        private val dataLocal: DataLocal,
        private val sharedViewPool: RecyclerView.RecycledViewPool
    ) : RecyclerView.ViewHolder(binding.root) {

        private var imageChildrenAdapter: ImageChildrenAdapter = ImageChildrenAdapter()
        var filteredList = mutableListOf<FileModel>()

        init {
            initRv(itemView)
        }

        fun bind(item: DateSelect, isUpdate: Boolean) {
            Log.d("MTEST", "bind: $absoluteAdapterPosition")
            when (item.date) {
                formatter.format(System.currentTimeMillis()) -> {
                    binding.tvName.text = itemView.context.getString(R.string.today)
                }
                formatter.format(System.currentTimeMillis() - 86400000) -> {
                    binding.tvName.text = itemView.context.getString(R.string.yesterday)
                }
                else -> binding.tvName.text = item.date
            }

//            filteredList = dataLocal.file.filter { it.dateCreated == item.date }.toMutableList()
            imageChildrenAdapter.submitList(dataLocal.file)

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
            binding.rvFullItem.apply {
                itemAnimator = null
                layoutManager = GridLayoutManager(binding.root.context, 4)
//                layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
                setRecycledViewPool(sharedViewPool)
            }
            binding.rvFullItem.adapter = imageChildrenAdapter

//            itemView.setOnClickListener {
//                binding.rvChildren.visibility= View.VISIBLE
//            }
        }
    }
}

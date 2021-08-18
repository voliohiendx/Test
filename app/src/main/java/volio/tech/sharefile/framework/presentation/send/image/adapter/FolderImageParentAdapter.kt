package volio.tech.sharefile.framework.presentation.send.image.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import volio.tech.sharefile.R
import volio.tech.sharefile.business.domain.DataLocal
import volio.tech.sharefile.business.domain.DateSelect
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.Folder
import volio.tech.sharefile.databinding.ItemImageParentBinding
import volio.tech.sharefile.util.DeviceDimensionsHelper.getDisplayWidth
import volio.tech.sharefile.util.convertDpToPx
import volio.tech.sharefile.util.gone
import volio.tech.sharefile.util.setPreventDoubleClick
import volio.tech.sharefile.util.show
import java.text.SimpleDateFormat

class FolderImageParentAdapter(
    val dataLocal: DataLocal,
    val lifecycleCoroutineScope: LifecycleCoroutineScope
) : ListAdapter<Folder, RecyclerView.ViewHolder>(FolderDiffUtilCallback()) {

    val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_parent, parent, false)
        val holder = ItemParent(view)
        holder.itemView.findViewById<RecyclerView>(R.id.rvFullItem).setRecycledViewPool(viewPool)
        return holder
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
        var filteredListAdapter = mutableListOf<FileModel>()

        init {
            initRv(itemView)
        }

        fun bind(item: Folder, isUpdate: Boolean) {
            binding.tvName.text = item.name
         //   filteredList = dataLocal.file.filter { it.idFolder == item.id }.toMutableList()
//
//            binding.rvFullItem.layoutParams.height =
//                (filteredList.size / 4) * 500
//            binding.rvFullItem.layoutParams.height = 50000

            //   delay(1000)
            imageChildrenAdapter?.submitList(dataLocal.file)
            if (!item.showFull) {
                binding.rvFullItem.gone()
            } else {
                binding.rvFullItem.show()
            }
            listener(item)
        }

        private fun listener(item: Folder) {
            itemView.setPreventDoubleClick {
                if (binding.rvFullItem.visibility == View.VISIBLE) {
                    binding.rvFullItem.gone()
                    item.showFull = false
                } else {
                    binding.rvFullItem.show()
                    item.showFull = true

                    //  imageChildrenAdapter?.submitList(filteredList)

//                    val listData = Pager(PagingConfig(pageSize = 6)) {
//                        PostDataSource(dataLocal.file)
//                    }.flow
//
//                    CoroutineScope(Dispatchers.Main).launch {
//                        listData.collectLatest { data ->
//                            imageChildrenAdapter?.submitData(data)
//                        }
//                    }
                }
            }
        }


        private fun initRv(itemView: View) {
            imageChildrenAdapter =
                ImageChildrenAdapter()

            binding.rvFullItem.apply {
                //     isAutoMeasureEnabled = false
                //    setHasFixedSize(true)
                layoutManager = GridLayoutManager(itemView.context, 4)

            }
            binding.rvFullItem.adapter = imageChildrenAdapter
            //    binding.rvFullItem.layoutParams.height = 50000

            Log.d("HiheiCAAA", "initRv: ")

//            itemView.setOnClickListener {
//                binding.rvChildren.visibility= View.VISIBLE
//            }
        }
    }
}
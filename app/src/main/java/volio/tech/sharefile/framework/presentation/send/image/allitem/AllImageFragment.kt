package volio.tech.sharefile.framework.presentation.send.image.allitem

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import volio.tech.sharefile.R
import volio.tech.sharefile.business.domain.DataLocal
import volio.tech.sharefile.business.domain.DateSelect
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.databinding.AllImageFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment
import volio.tech.sharefile.framework.presentation.send.image.smooth.SmoothImageAdapter
import volio.tech.sharefile.framework.presentation.send.image.smooth.viewdata.ImageHeaderViewData
import volio.tech.sharefile.framework.presentation.send.image.smooth.viewdata.ImageRowViewData
import volio.tech.sharefile.framework.presentation.send.image.smooth.viewdata.ImageViewData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AllImageFragment : BaseFragment<AllImageFragmentBinding>(AllImageFragmentBinding::inflate),
    SmoothImageAdapter.SmoothCallback {

    override fun init(view: View) {

    }

    override fun subscribeObserver(view: View) {
        dataLocalViewModel.allImage.observe(viewLifecycleOwner, {
            it?.let {
                handleData(it)
            }
        })
    }

    //move vào viewmodel
    private fun handleData(data: DataLocal) {
        CoroutineScope(Main).launch {
            val newList = withContext(Default) {
                parseImageData(data)
            }
            val smoothAdapter = SmoothImageAdapter(this@AllImageFragment)
            binding.rvParentImage.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = smoothAdapter
            }

            smoothAdapter.submitList(newList)
        }
    }

    /**
     * xử lí data
     * RecyclerView dạng
     * [ImageHeaderViewData] : header: title
     * [ImageRowViewData] : ảnh: list các list ảnh ( 4 ảnh 1 )
     * [ImageHeaderViewData] : header
     * [ImageRowViewData] : ảnh
     * ...
     */
    private fun parseImageData(data: DataLocal): ArrayList<ImageViewData> {
        val list = ArrayList<ImageViewData>()

        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        data.listDate.forEach { item ->
            //lấy header
            list.add(getHeaderByDate(formatter, item))

            //lấy list images chia theo date
//            val filteredList = data.file.filter { it.dateCreated == item.date }.toMutableList()
//            list.addAll(splitListTo(4, filteredList))

            //test: dùng full list test performance
            list.addAll(splitListTo(4, data.file))
        }

        return list
    }

    private fun getHeaderByDate(
        formatter: SimpleDateFormat,
        item: DateSelect
    ): ImageHeaderViewData {
        val title = when (item.date) {
            formatter.format(System.currentTimeMillis()) -> {
                context?.getString(R.string.today).orEmpty()
            }
            formatter.format(System.currentTimeMillis() - 86400000) -> {
                context?.getString(R.string.yesterday).orEmpty()
            }
            else -> {
                item.date
            }
        }
        return ImageHeaderViewData("header_${item.date}", title)
    }

    private fun splitListTo(into: Int, list: List<FileModel>): ArrayList<ImageRowViewData> {
        val newList = ArrayList<ImageRowViewData>()
        val tempList = ArrayList<FileModel>()
        var tempCount = 0

        for ((index, item) in list.withIndex()) {
            tempList.add(item)

            tempCount++
            if (tempCount == into || index == list.size - 1) {
                newList.add(ImageRowViewData(UUID.randomUUID().toString(), ArrayList(tempList)))
                tempList.clear()
                tempCount = 0
            }
        }

        return newList
    }

    override fun onHeaderSelected(item: ImageHeaderViewData) {
        Toast.makeText(requireContext(), "select ${item.title}", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = AllImageFragment()
    }

}

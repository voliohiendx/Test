package volio.tech.sharefile.framework.presentation.send.image.foldes

import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.flow.collectLatest
import volio.tech.sharefile.business.domain.DataLocal
import volio.tech.sharefile.databinding.FoldesImageFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment
import volio.tech.sharefile.framework.presentation.send.image.adapter.AllImageParentAdapter
import volio.tech.sharefile.framework.presentation.send.image.adapter.FolderImageParentAdapter
import volio.tech.sharefile.framework.presentation.send.image.adapter.PostDataSourceNew
import volio.tech.sharefile.framework.presentation.send.image.adapter.paging.PagingImageParentAdapter

class FolderImageFragment(
) :
    BaseFragment<FoldesImageFragmentBinding>(FoldesImageFragmentBinding::inflate) {

    var folderImageParentAdapter: FolderImageParentAdapter? = null
    var pagingImageParentAdapter: PagingImageParentAdapter? = null

    override fun init(view: View) {

    }

    override fun subscribeObserver(view: View) {
        dataLocalViewModel.allImage.observe(viewLifecycleOwner, {
            it?.let {
                Log.d("Hieniaccas", "subscribeObserver: ")
                initRv(it)
              //  initRvNew(it)

//                val listData = Pager(PagingConfig(pageSize = 6)) {
//                    PostDataSourceNew(it.folder)
//                }.flow
//
//                viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//                    listData.collectLatest { data ->
//                        pagingImageParentAdapter?.submitData(data)
//                    }
//                }
            }
        })
    }

    companion object {
        fun newInstance() = FolderImageFragment()
    }

    fun initRvNew(dataLocal: DataLocal) {
        pagingImageParentAdapter = PagingImageParentAdapter(dataLocal, viewLifecycleOwner.lifecycleScope)
        binding.rvParentFolder.apply {
            layoutManager = object : LinearLayoutManager(context, VERTICAL, false) {}
           // setHasFixedSize(true)
        }
        binding.rvParentFolder.adapter = pagingImageParentAdapter
        Log.d("HienOcaaa", dataLocal.folder.toString())
     //   folderImageParentAdapter!!.submitList(dataLocal.folder)
    }

}

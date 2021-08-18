package volio.tech.sharefile.framework.presentation.send.image.allitem

import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.flow.collectLatest
import volio.tech.sharefile.databinding.AllImageFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment
import volio.tech.sharefile.framework.presentation.send.image.adapter.AllImageParentAdapter
import volio.tech.sharefile.framework.presentation.send.image.adapter.ImageChildrenAdapter
import volio.tech.sharefile.framework.presentation.send.image.adapter.PostDataSource

class AllImageFragment(
) : BaseFragment<AllImageFragmentBinding>(AllImageFragmentBinding::inflate) {

    var imageParentAdapter: AllImageParentAdapter? = null
    private var imageChildrenAdapter: ImageChildrenAdapter? = null

    override fun init(view: View) {


    }

    override fun subscribeObserver(view: View) {
        dataLocalViewModel.allImage.observe(viewLifecycleOwner, {
            it?.let {
                initRv(it)
//                initRvNew()
//                val listData = Pager(PagingConfig(pageSize = 6)) {
//                    PostDataSource(it.file)
//                }.flow
//
//                viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//                    listData.collectLatest { data ->
//                        imageChildrenAdapter?.submitData(data)
//                    }
//                }
            }
        })
    }

    private fun initRvNew() {
        imageChildrenAdapter =
            ImageChildrenAdapter()

        binding.rvParentImage.apply {
            //     isAutoMeasureEnabled = false
            //    setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 4)

        }
        binding.rvParentImage.adapter = imageChildrenAdapter
        //    binding.rvFullItem.layoutParams.height = 50000

        Log.d("HiheiCAAA", "initRv: ")

//            itemView.setOnClickListener {
//                binding.rvChildren.visibility= View.VISIBLE
//            }
    }

    companion object {
        fun newInstance() = AllImageFragment()
    }
}

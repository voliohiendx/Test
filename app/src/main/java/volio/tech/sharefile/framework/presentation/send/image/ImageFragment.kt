package volio.tech.sharefile.framework.presentation.send.image

import android.view.View
import volio.tech.sharefile.databinding.FragmentSelectImgeBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class ImageFragment :
    BaseFragment<FragmentSelectImgeBinding>(FragmentSelectImgeBinding::inflate) {
    override fun init(view: View) {
        initTabsWithViewPager()
    }

    override fun subscribeObserver(view: View) {
        dataLocalViewModel.allImage.observe(viewLifecycleOwner,{
            it?.let {

            }
        })
    }

    companion object {
        fun newInstance() = ImageFragment().apply {
            val selectImageFragment = ImageFragment()
            return selectImageFragment
        }
    }
}

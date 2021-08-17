package volio.tech.sharefile.framework.presentation.send.file

import android.view.View
import volio.tech.sharefile.databinding.FileFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class FileFragment : BaseFragment<FileFragmentBinding>(FileFragmentBinding::inflate) {

    override fun init(view: View) {
        initTabsWithViewPager()
    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = FileFragment()
    }

}
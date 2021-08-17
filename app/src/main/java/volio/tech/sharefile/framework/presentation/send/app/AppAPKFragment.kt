package volio.tech.sharefile.framework.presentation.send.app

import android.view.View
import volio.tech.sharefile.databinding.AppAPKFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class AppAPKFragment : BaseFragment<AppAPKFragmentBinding>(AppAPKFragmentBinding::inflate) {

    override fun init(view: View) {
        initTabsWithViewPager()
    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = AppAPKFragment()
    }


}
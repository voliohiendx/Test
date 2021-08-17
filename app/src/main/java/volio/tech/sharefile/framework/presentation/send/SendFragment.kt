package volio.tech.sharefile.framework.presentation.send

import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import volio.tech.sharefile.databinding.SendFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

@AndroidEntryPoint
class SendFragment : BaseFragment<SendFragmentBinding>(SendFragmentBinding::inflate) {

    override fun init(view: View) {
        initTabsWithViewPager()
    }

    override fun subscribeObserver(view: View) {

    }

    companion object {

    }

}

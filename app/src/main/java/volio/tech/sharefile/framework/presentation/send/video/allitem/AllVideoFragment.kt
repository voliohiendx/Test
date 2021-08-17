package volio.tech.sharefile.framework.presentation.send.video.allitem

import android.view.View
import volio.tech.sharefile.databinding.AllImageFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class AllVideoFragment : BaseFragment<AllImageFragmentBinding>(AllImageFragmentBinding::inflate) {

    override fun init(view: View) {

    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = AllVideoFragment()
    }
}

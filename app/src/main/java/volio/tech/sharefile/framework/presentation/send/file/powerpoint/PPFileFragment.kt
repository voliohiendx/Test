package volio.tech.sharefile.framework.presentation.send.file.powerpoint

import android.view.View
import volio.tech.sharefile.databinding.PPFileFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class PPFileFragment : BaseFragment<PPFileFragmentBinding>(PPFileFragmentBinding::inflate) {

    override fun init(view: View) {

    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = PPFileFragment()
    }

}

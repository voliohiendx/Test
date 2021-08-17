package volio.tech.sharefile.framework.presentation.send.app.allapk

import android.view.View
import volio.tech.sharefile.databinding.AllAPKFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class AllAPKFragment : BaseFragment<AllAPKFragmentBinding>(AllAPKFragmentBinding::inflate) {

    override fun init(view: View) {

    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = AllAPKFragment()
    }

}
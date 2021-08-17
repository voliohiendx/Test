package volio.tech.sharefile.framework.presentation.send.app.allapp

import android.view.View
import volio.tech.sharefile.databinding.AllAppFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class AllAppFragment : BaseFragment<AllAppFragmentBinding>(AllAppFragmentBinding::inflate) {

    override fun init(view: View) {

    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = AllAppFragment()
    }


}
package volio.tech.sharefile.framework.presentation.send.file.doc

import android.view.View
import volio.tech.sharefile.databinding.DocFileFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class DocFileFragment : BaseFragment<DocFileFragmentBinding>(DocFileFragmentBinding::inflate) {

    override fun init(view: View) {

    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = DocFileFragment()
    }

}
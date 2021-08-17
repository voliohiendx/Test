package volio.tech.sharefile.framework.presentation.send.file.pdf

import android.view.View
import volio.tech.sharefile.databinding.PdfFileFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class PdfFileFragment : BaseFragment<PdfFileFragmentBinding>(PdfFileFragmentBinding::inflate) {

    override fun init(view: View) {

    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = PdfFileFragment()
    }

}
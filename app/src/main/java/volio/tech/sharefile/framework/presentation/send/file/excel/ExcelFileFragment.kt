package volio.tech.sharefile.framework.presentation.send.file.excel

import android.view.View
import androidx.fragment.app.Fragment
import volio.tech.sharefile.databinding.ExcelFileFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class ExcelFileFragment : BaseFragment<ExcelFileFragmentBinding>(ExcelFileFragmentBinding::inflate) {

    override fun init(view: View) {

    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = ExcelFileFragment()
    }

}
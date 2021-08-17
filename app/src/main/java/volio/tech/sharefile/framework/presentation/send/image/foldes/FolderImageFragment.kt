package volio.tech.sharefile.framework.presentation.send.image.foldes

import android.view.View
import volio.tech.sharefile.databinding.FoldesImageFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class FolderImageFragment : BaseFragment<FoldesImageFragmentBinding>(FoldesImageFragmentBinding::inflate) {

    override fun init(view: View) {

    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = FolderVideoFragment()
    }

}

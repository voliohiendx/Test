package volio.tech.sharefile.framework.presentation.send.music

import android.view.View
import volio.tech.sharefile.databinding.MusicFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class MusicFragment : BaseFragment<MusicFragmentBinding>(MusicFragmentBinding::inflate) {

    override fun init(view: View) {
        initTabsWithViewPager()
    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = MusicFragment()
    }

}

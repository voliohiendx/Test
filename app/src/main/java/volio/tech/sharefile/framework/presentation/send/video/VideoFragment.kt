package volio.tech.sharefile.framework.presentation.send.video

import android.view.View
import volio.tech.sharefile.databinding.VideoFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

class VideoFragment : BaseFragment<VideoFragmentBinding>(VideoFragmentBinding::inflate) {

    override fun init(view: View) {
        initTabsWithViewPager()
    }

    override fun subscribeObserver(view: View) {

    }

    companion object {
        fun newInstance() = VideoFragment()
    }

}
package volio.tech.sharefile.framework.presentation.send.video.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import volio.tech.sharefile.framework.presentation.send.image.foldes.FolderVideoFragment
import volio.tech.sharefile.framework.presentation.send.video.VideoFragment
import volio.tech.sharefile.framework.presentation.send.video.allitem.AllVideoFragment

class VideoPagerFragment(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return AllVideoFragment.newInstance()
            }
            else -> return FolderVideoFragment.newInstance()
        }

    }
}

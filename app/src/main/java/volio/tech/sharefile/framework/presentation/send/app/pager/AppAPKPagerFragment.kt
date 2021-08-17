package volio.tech.sharefile.framework.presentation.send.app.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import volio.tech.sharefile.framework.presentation.send.app.allapk.AllAPKFragment
import volio.tech.sharefile.framework.presentation.send.app.allapp.AllAppFragment
import volio.tech.sharefile.framework.presentation.send.image.foldes.FolderVideoFragment
import volio.tech.sharefile.framework.presentation.send.music.allitem.AllMusicFragment
import volio.tech.sharefile.framework.presentation.send.music.foldes.FolderMusicFragment

class AppAPKPagerFragment(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return AllAppFragment.newInstance()
            }
            else -> return AllAPKFragment.newInstance()
        }

    }
}

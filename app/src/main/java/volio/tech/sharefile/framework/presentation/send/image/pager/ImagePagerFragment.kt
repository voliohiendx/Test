package volio.tech.sharefile.framework.presentation.send.image.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import volio.tech.sharefile.framework.presentation.send.image.allitem.AllImageFragment
import volio.tech.sharefile.framework.presentation.send.image.foldes.FolderImageFragment

class ImagePagerFragment(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return AllImageFragment.newInstance()
            }
            else -> return FolderImageFragment.newInstance()
        }

    }
}

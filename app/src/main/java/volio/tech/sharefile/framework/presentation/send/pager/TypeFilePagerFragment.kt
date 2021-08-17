package volio.tech.sharefile.framework.presentation.send.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import volio.tech.sharefile.framework.presentation.send.app.AppAPKFragment
import volio.tech.sharefile.framework.presentation.send.file.FileFragment
import volio.tech.sharefile.framework.presentation.send.image.ImageFragment
import volio.tech.sharefile.framework.presentation.send.music.MusicFragment
import volio.tech.sharefile.framework.presentation.send.video.VideoFragment

class TypeFilePagerFragment(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return ImageFragment.newInstance()
            }
            1 -> {
                return VideoFragment.newInstance()
            }
            2 -> {
                return MusicFragment.newInstance()
            }
            3 -> {
                return AppAPKFragment.newInstance()
            }
            else -> return FileFragment.newInstance()
        }

    }
}

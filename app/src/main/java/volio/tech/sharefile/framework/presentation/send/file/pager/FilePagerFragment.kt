package volio.tech.sharefile.framework.presentation.send.file.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import volio.tech.sharefile.framework.presentation.send.file.doc.DocFileFragment
import volio.tech.sharefile.framework.presentation.send.file.excel.ExcelFileFragment
import volio.tech.sharefile.framework.presentation.send.file.pdf.PdfFileFragment
import volio.tech.sharefile.framework.presentation.send.file.powerpoint.PPFileFragment
import volio.tech.sharefile.framework.presentation.send.image.foldes.FolderVideoFragment
import volio.tech.sharefile.framework.presentation.send.music.allitem.AllMusicFragment
import volio.tech.sharefile.framework.presentation.send.music.foldes.FolderMusicFragment

class FilePagerFragment(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return DocFileFragment.newInstance()
            }
            1 -> {
                return ExcelFileFragment.newInstance()
            }
            2 -> {
                return PdfFileFragment.newInstance()
            }
            else -> return PPFileFragment.newInstance()
        }

    }
}

package volio.tech.sharefile.framework.presentation.send.music

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import volio.tech.sharefile.framework.presentation.send.music.pager.MusicPagerFragment
import volio.tech.sharefile.framework.presentation.send.video.pager.VideoPagerFragment

fun MusicFragment.initTabsWithViewPager() {

    val listType = mutableListOf<String>()

    listType.add("All Music")
    listType.add("Folders")

    val tabs: TabLayout = binding.tbMusic
    val viewPager: ViewPager2 = binding.vpMusic
    viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    viewPager.isUserInputEnabled = true
    viewPager.offscreenPageLimit = 1
    viewPager.adapter = MusicPagerFragment(this)
    viewPager.isSaveEnabled = false

    TabLayoutMediator(tabs, viewPager)
    { tab: TabLayout.Tab, position: Int ->
        tab.text = listType[position]
    }.attach()

    tabs.setOnClickListener {
        //Log.d("HienDXXXX", "initTabsWithViewPager: ")
    }

    viewPager.registerOnPageChangeCallback(
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

            }
        })
}

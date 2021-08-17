package volio.tech.sharefile.framework.presentation.send.video

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import volio.tech.sharefile.framework.presentation.send.video.pager.VideoPagerFragment

fun VideoFragment.initTabsWithViewPager() {

    val listType = mutableListOf<String>()

    listType.add("All items")
    listType.add("Folders")

    val tabs: TabLayout = binding.tbVideo
    val viewPager: ViewPager2 = binding.vpVideo
    viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    viewPager.isUserInputEnabled = true
    viewPager.offscreenPageLimit = 1
    viewPager.adapter = VideoPagerFragment(this)
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

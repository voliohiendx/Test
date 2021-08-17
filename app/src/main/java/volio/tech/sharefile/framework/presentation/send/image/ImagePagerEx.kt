package volio.tech.sharefile.framework.presentation.send.image

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import volio.tech.sharefile.framework.presentation.send.image.pager.ImagePagerFragment
import volio.tech.sharefile.framework.presentation.send.video.pager.VideoPagerFragment

fun ImageFragment.initTabsWithViewPager() {

    val listType = mutableListOf<String>()

    listType.add("All items")
    listType.add("Folders")

    val tabs: TabLayout = binding.tbImage
    val viewPager: ViewPager2 = binding.vpImage
    viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    viewPager.isUserInputEnabled = true
    viewPager.offscreenPageLimit = 1
    viewPager.adapter = ImagePagerFragment(this)
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

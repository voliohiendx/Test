package volio.tech.sharefile.framework.presentation.send

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import volio.tech.sharefile.framework.presentation.send.pager.TypeFilePagerFragment

fun SendFragment.initTabsWithViewPager() {

    val listType = mutableListOf<String>()

    listType.add("Image")
    listType.add("Video")
    listType.add("Music")
    listType.add("App/APK")
    listType.add("File")

    val tabs: TabLayout = binding.tbTypeFile
    val viewPager: ViewPager2 = binding.vpTypeFile
    viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    viewPager.isUserInputEnabled = false
    viewPager.offscreenPageLimit = 1
    viewPager.adapter = TypeFilePagerFragment(this)
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

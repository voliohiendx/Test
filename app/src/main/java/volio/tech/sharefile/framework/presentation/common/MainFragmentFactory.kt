package volio.tech.sharefile.framework.presentation.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import volio.tech.sharefile.framework.presentation.hiendx.HienDxFragment
import volio.tech.sharefile.framework.presentation.send.SendFragment
import volio.tech.sharefile.framework.presentation.splash.SplashFragment
import javax.inject.Inject

class MainFragmentFactory
@Inject
constructor(
    private val glide: RequestManager
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            SplashFragment::class.java.name -> {
                SplashFragment(glide)
            }

            SendFragment::class.java.name -> {
                SendFragment()
            }

            HienDxFragment::class.java.name -> {
                HienDxFragment(glide)
            }

            else -> super.instantiate(classLoader, className)
        }

    }
}
package volio.tech.sharefile.framework.presentation.splash

import android.view.View
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import volio.tech.sharefile.databinding.FragmentSplashBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment

@AndroidEntryPoint
class SplashFragment(
    private val glide: RequestManager
) : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun init(view: View) {
    }

    override fun subscribeObserver(view: View) {
    }

    companion object {
        const val TAG = "AppDebug"
    }

}

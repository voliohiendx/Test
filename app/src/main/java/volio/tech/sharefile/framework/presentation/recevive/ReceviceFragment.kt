package volio.tech.sharefile.framework.presentation.recevive

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import volio.tech.sharefile.R

class ReceviceFragment : Fragment() {

    companion object {
        fun newInstance() = ReceviceFragment()
    }

    private lateinit var viewModel: ReceviceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recevice_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReceviceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
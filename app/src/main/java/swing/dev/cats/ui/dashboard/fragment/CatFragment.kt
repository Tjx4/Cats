package swing.dev.cats.ui.dashboard.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.entersekt.shopfinder.helpers.loadImageFromInternet
import swing.dev.cats.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import swing.dev.cats.base.fragments.BaseDialogFragment
import swing.dev.cats.databinding.FragmentCartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import swing.dev.cats.R
import swing.dev.cats.constants.URL

class CatFragment : BaseDialogFragment() {
    private lateinit var binding: FragmentCartBinding
    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.lifecycleOwner = this
        binding.dashboardViewModel = dashboardViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgbClose.setOnClickListener { dismiss() }
        val url = arguments?.getString(URL) ?: ""
        loadImageFromInternet(requireContext(), url, imgCatLarge, R.drawable.ic_def)
    }

    companion object {
        fun newInstance(): BaseDialogFragment {
            return CatFragment()
        }
    }

}
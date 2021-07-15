package swing.dev.cats.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import swing.dev.cats.adapters.CatLoadStateAdapter
import swing.dev.cats.ui.dashboard.fragment.CatFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import swing.dev.cats.R
import swing.dev.cats.adapters.CatsPagingAdapter
import swing.dev.cats.base.fragments.BaseFragment
import swing.dev.cats.constants.URL
import swing.dev.cats.databinding.FragmentDashboardBinding
import swing.dev.cats.extensions.getScreenCols
import swing.dev.cats.helpers.showDialogFragment
import swing.dev.cats.persistance.room.tables.cats.CatImageTable

class DashboardFragment : BaseFragment(), CatsPagingAdapter.AddCatClickListener {
    private lateinit var binding: FragmentDashboardBinding
    private val dashboardViewModel: DashboardViewModel by viewModel()
    private lateinit var catsPagingAdapter: CatsPagingAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        catsPagingAdapter = CatsPagingAdapter(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        binding.lifecycleOwner = this
        binding.dashboardViewModel = dashboardViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()

        btnRetry.setOnClickListener { catsPagingAdapter.refresh() }

        val cols = requireActivity().getScreenCols(125f)
        rvCities.apply {
            layoutManager = GridLayoutManager(context, cols)
            setHasFixedSize(true)
            adapter = catsPagingAdapter.withLoadStateFooter(footer = CatLoadStateAdapter(catsPagingAdapter))
        }

        catsPagingAdapter.addPairClickListener(this)

        lifecycleScope.launch {
            dashboardViewModel.catImages.collectLatest {
                catsPagingAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            catsPagingAdapter.loadStateFlow.collectLatest { loadState ->
                when (loadState.refresh) {
                    is LoadState.Error -> {
                        val error = when {
                            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                            else -> null
                        }

                        error?.let {
                            val message = if (it.error.message.isNullOrEmpty()) getString(R.string.cats_error) else it.error.message!!
                            onError(message)
                        }
                    }
                    is LoadState.Loading -> onLoading(true)
                    is LoadState.NotLoading -> { showContent() }
                }
            }
        }
    }

    private fun addObservers() {
        dashboardViewModel.isLoading.observe(viewLifecycleOwner, { onLoading(it)})
        dashboardViewModel.error.observe(viewLifecycleOwner, { onError(it)})
    }

    private fun onLoading(isLoading: Boolean) {
        llError.visibility = View.GONE
        loader.visibility = View.VISIBLE
        rvCities.visibility = View.GONE
    }

    private fun onError(errorMessage: String) {
        llError.visibility = View.VISIBLE
        loader.visibility = View.GONE
        rvCities.visibility = View.GONE
    }

    private fun showContent() {
        llError.visibility = View.GONE
        loader.visibility = View.GONE
        rvCities.visibility = View.VISIBLE
    }

    override fun onCatClickListener(catImageTable: CatImageTable) {
        val catFragment = CatFragment.newInstance()
        val payload = Bundle()
        payload.putString(URL, catImageTable.url)
        catFragment.arguments = payload
        catFragment.isCancelable = true
        showDialogFragment(catFragment, requireActivity())
    }
}
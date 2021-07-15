package swing.dev.cats.ui.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import swing.dev.cats.ui.dashboard.paging.CatImagesPagingSource
import com.platform45.fx45.base.viewmodel.BaseVieModel
import swing.dev.cats.constants.PAGE_SIZE
import swing.dev.cats.repository.CatService

class DashboardViewModel(application: Application, private val catService: CatService) : BaseVieModel(application) {
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean>
        get() = _isLoading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: MutableLiveData<String>
        get() = _error

    val catImages = Pager(config = PagingConfig(pageSize = PAGE_SIZE)) {
        CatImagesPagingSource(catService)
    }.flow.cachedIn(viewModelScope)

}
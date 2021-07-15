package swing.dev.cats.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import swing.dev.cats.ui.dashboard.DashboardViewModel

val viewModelModule = module {
    viewModel { DashboardViewModel(androidApplication(), get()) }
}

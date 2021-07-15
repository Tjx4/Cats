package swing.dev.cats

import android.app.Application
import com.entersekt.shopfinder.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import swing.dev.cats.di.*

class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    persistenceModule,
                    networkingModule
                ) + ModuleLoadHelper.getBuildSpecialModuleList()
            )
        }
    }
}
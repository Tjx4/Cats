package swing.dev.cats.di

import com.entersekt.outlets.retrofit.API
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import swing.dev.cats.persistance.room.CDb

val networkingModule = module {
    single { API.retrofit }
}

val persistenceModule = module {
    single { CDb.getInstance(androidApplication())}
}
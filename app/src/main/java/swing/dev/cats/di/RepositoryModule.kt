package swing.dev.cats.di

import swing.dev.cats.repository.CatService
import org.koin.dsl.module

val repositoryModule = module {
    single { CatService(get()) }
}
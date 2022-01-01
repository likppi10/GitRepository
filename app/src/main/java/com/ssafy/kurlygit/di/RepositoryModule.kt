package com.ssafy.kurlygit

import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}
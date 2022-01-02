package com.ssafy.kurlygit

import com.ssafy.kurlygit.data.repository.MainRepository
import com.ssafy.kurlygit.data.repository.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<MainRepository> { MainRepositoryImpl(get()) }
}
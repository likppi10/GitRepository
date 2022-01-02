package com.ssafy.kurlygit.di

import com.ssafy.kurlygit.data.api.RepositoryService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single{ OkHttpClient.Builder().build() }

    single<Retrofit>{
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build()
    }

    single<RepositoryService> {
        get<Retrofit>().create(RepositoryService::class.java)
    }
}
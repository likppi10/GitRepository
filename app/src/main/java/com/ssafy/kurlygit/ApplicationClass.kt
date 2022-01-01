package com.ssafy.kurlygit

import android.app.Application
import android.content.Context
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ApplicationClass)
            modules(
                viewModelModule
                , repositoryModule
                , networkModule
            )
        }
    }

    companion object{

        /* 현재 API에서 불러온 페이지 수와 최근 검색한 단어를 저장합니다.*/
        var nowPage = 1
        var recentWord = ""
        /* 더 표시할 것이 없음을 알려줍니다.*/
        var stopThisIsEnd = false

        var appContext: Context? = null
            private set
    }
}
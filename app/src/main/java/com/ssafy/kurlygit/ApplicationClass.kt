package com.ssafy.kurlygit

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass: Application() {

    companion object{

        const val SERVER_URL = "https://api.github.com/"

        /* 현재 API에서 불러온 페이지 수와 최근 검색한 단어를 저장합니다.*/
        var nowPage = 1
        var recentWord = ""

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS).build()

        // 앱이 처음 생성되는 순간, retrofit을 생성합니다.
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    }
}
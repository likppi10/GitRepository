package com.ssafy.kurlygit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryService {

    fun getRepositories(search: String, callback: BaseCallback<Repositories>){

        // Github API와 통신하기 직전의 지점으로 다른 단어를 새로 검색한 경우 페이지를 1로 지정해줘야 합니다.
        if(ApplicationClass.recentWord!=search){
            ApplicationClass.nowPage = 1
        }

        val repositoriesList: Call<Repositories> = RetrofitUtil.repositoryService.getRepositories(search, ApplicationClass.nowPage)

        // Github API와 통신하고 나서는 페이지를 1을 늘려줍니다.
        ApplicationClass.nowPage++

        repositoriesList.enqueue(object : Callback<Repositories> {
            override fun onResponse(call: Call<Repositories>, response: Response<Repositories>) {

                val res = response.body()
                if (response.code() == 200) {
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<Repositories>, t: Throwable) {
                callback.onError(t)
            }
        })
    }


}
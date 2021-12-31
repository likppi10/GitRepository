package com.ssafy.kurlygit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryService {

    fun getRepositories(search: String, callback: BaseCallback<Repositories>){

        val repositoriesList: Call<Repositories> = RetrofitUtil.repositoryService.getRepositories(search)

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
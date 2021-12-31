package com.ssafy.kurlygit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryAPI {
    @GET("search/repositories")
    fun getRepositories(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Call<Repositories>
}
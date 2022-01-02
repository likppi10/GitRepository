package com.ssafy.kurlygit.data.api
import com.ssafy.kurlygit.entity.RepositoriesEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    /* q : 검색어
    *  page : 현재 검색 결과 페이지
    *  한 page에는 최대 30개의 결과를 return 합니다.*/
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("page") page: Int
    ): RepositoriesEntity
}
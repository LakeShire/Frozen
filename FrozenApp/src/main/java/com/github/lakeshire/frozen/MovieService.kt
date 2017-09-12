package com.github.lakeshire.frozen

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface MovieService {

    @GET("top250")
    fun getTopMovie(@Query("start") start: Int, @Query("count") count: Int): Observable<ListEntity<Movie>>
}

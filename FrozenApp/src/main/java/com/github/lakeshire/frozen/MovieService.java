package com.github.lakeshire.frozen;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liuhan on 17/9/1.
 */

public interface MovieService {

    @GET("top250")
    Observable<ListEntity<Movie>> getTopMovie(@Query("start") int start, @Query("count") int count);
}

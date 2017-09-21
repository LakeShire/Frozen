package com.github.lakeshire.frozen.manager.http

import com.github.lakeshire.frozen.model.ListEntity
import com.github.lakeshire.frozen.model.Movie

import lakeshire.github.com.frozenframework.manager.RetrofitManager
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * 网络请求管理器

 * @author lakeshire
 */
class HttpManager {

    val movies: Observable<ListEntity<Movie>>
        get() {
            val service = RetrofitManager.getInstance().create(MovieService::class.java)
            return service.getTopMovie(0, 10)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }

    /**
     * 处理标准的请求返回
     */
    internal inner class HttpResultFunc<T> : Func1<ListEntity<T>, ListEntity<T>> {

        override fun call(entity: ListEntity<T>): ListEntity<T> {
            return entity
        }
    }

    companion object {
        var sManager: HttpManager = HttpManager()
    }
}

package com.github.lakeshire.frozen.manager;

import com.github.lakeshire.frozen.ListEntity;
import com.github.lakeshire.frozen.Movie;
import com.github.lakeshire.frozen.MovieService;

import lakeshire.github.com.frozenframework.manager.RetrofitManager;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 网络请求管理器
 *
 * @author lakeshire
 */
public class HttpManager {

    private static HttpManager sManager;

    public static HttpManager getInstance() {
        if (sManager == null) {
            synchronized (HttpManager.class) {
                if (sManager == null) {
                    sManager = new HttpManager();
                }
            }
        }
        return sManager;
    }

    public Observable<ListEntity<Movie>> getMovies() {
        MovieService service = RetrofitManager.getInstance().create(MovieService.class);
        return service.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 处理标准的请求返回
     *
     * @param <T>
     */
    class HttpResultFunc<T> implements Func1<ListEntity<T>, ListEntity<T>> {

        @Override
        public ListEntity<T> call(ListEntity<T> entity) {
            return entity;
        }
    }
}

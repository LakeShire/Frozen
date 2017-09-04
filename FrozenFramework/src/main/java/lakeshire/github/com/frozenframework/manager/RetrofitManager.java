package lakeshire.github.com.frozenframework.manager;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit管理器
 *
 * @author lakeshire
 */
public class RetrofitManager {

    private static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final long DEFAULT_TIME_OUT = 10000;

    private Retrofit mRetrofit;
    private static RetrofitManager sManager;

    public static RetrofitManager getInstance() {
        if (sManager == null) {
            synchronized (RetrofitManager.class) {
                if (sManager == null) {
                    sManager = new RetrofitManager();
                }
            }
        }
        return sManager;
    }

    private RetrofitManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BASIC;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.d(message);
            }
        });
        loggingInterceptor.setLevel(level);
        builder.addInterceptor(loggingInterceptor);


        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

}

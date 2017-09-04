package com.github.lakeshire.frozen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lakeshire.frozen.manager.HttpManager;
import com.github.lakeshire.frozen.manager.MainFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import lakeshire.github.com.frozenframework.activity.BaseActivity;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction().add(R.id.container, fragment).commit();
        }
//        final List<String> list = new ArrayList<>();
//        list.add("A");
//        list.add("B");
//        list.add("C");
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        CommonAdapter<String> adapter = new CommonAdapter<String>(this, R.layout.item, list) {
//            @Override
//            protected void convert(ViewHolder holder, String s, int position) {
//                holder.setText(R.id.tv_title, s);
//            }
//        };
//        recyclerView.setAdapter(adapter);
//
//        HttpManager.getInstance().getMovies()
//                .flatMap(new Func1<ListEntity<Movie>, Observable<Movie>>() {
//                    @Override
//                    public Observable<Movie> call(final ListEntity<Movie> movieEntity) {
//                        if (movieEntity != null) {
//                            return Observable.create(new Observable.OnSubscribe<Movie>() {
//                                @Override
//                                public void call(Subscriber<? super Movie> subscriber) {
//                                    for (Movie subject : movieEntity.list) {
//                                        subscriber.onNext(subject);
//                                    }
//                                }
//                            });
//                        }
//                        throw new RuntimeException();
//                    }
//                })
//                .subscribe(new Subscriber<Movie>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Movie subject) {
//
//                    }
//                });
    }
}

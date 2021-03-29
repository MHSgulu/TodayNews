package com.design.todaynews.util.network;

import com.design.todaynews.common.Constant;
import com.design.todaynews.model.HotSpot;
import com.design.todaynews.model.News;
import com.design.todaynews.model.NewsCategory;
import com.design.todaynews.model.NewsChannel;
import com.design.todaynews.model.NewsSearch;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonRetrofit {

    private final APIService apiService2;


    private SingletonRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .build();

        //京东万象API
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(Constant.JDAPI_URL)
                //.client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //京东万象Service
        apiService2 = retrofit2.create(APIService.class);


    }

    //获取单例
    public static SingletonRetrofit getInstance() {
        return Wonderland.instance;
    }


    //单例  静态内部类模式    在访问SingletonRetrofit时创建单例
    private static class Wonderland {
        private static final SingletonRetrofit instance = new SingletonRetrofit();
    }


    /**
     * 获取 新闻
     *
     * @param observer 由调用者传过来的观察者对象
     * @param channel  频道
     * @param num      数量 默认10，最大40
     * @param start    起始位置，默认0
     * @param appKey   key值
     */
    public void getNews(Observer<News> observer, String channel, String num, String start, String appKey) {
        apiService2.getNews(channel, num, start, appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 获取 新闻频道
     *
     * @param observer 由调用者传过来的观察者对象
     * @param appKey   key值
     */
    public void getNewsChannel(Observer<NewsChannel> observer, String appKey) {
        apiService2.getNewsChannel(appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 获取 关键词查询新闻
     *
     * @param observer 由调用者传过来的观察者对象
     * @param keyword  关键词
     * @param appKey   key值
     */
    public void getSearchNews(Observer<NewsSearch> observer, String keyword, String appKey) {
        apiService2.getSearchNews(keyword, appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 查询  热词排行
     *
     * @param observer 由调用者传过来的观察者对象
     * @param typeId   分类id
     * @param appKey   key值
     */
    public void getHotSpotRanking(Observer<HotSpot> observer, String typeId, String appKey) {
        apiService2.getHotSpotRanking(typeId, appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 查询  热点词分类
     *
     * @param observer 由调用者传过来的观察者对象
     * @param appKey   key值
     */
    public void fetchNewsCategory(Observer<NewsCategory> observer, String appKey) {
        apiService2.getNewsCategory(appKey)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}

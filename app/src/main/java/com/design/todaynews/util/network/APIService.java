package com.design.todaynews.util.network;


import com.design.todaynews.model.HotSpot;
import com.design.todaynews.model.News;
import com.design.todaynews.model.NewsCategory;
import com.design.todaynews.model.NewsChannel;
import com.design.todaynews.model.NewsSearch;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {


    /*
     *   新闻API     限1000次/天
     */

    /**
     * 京东万象  按照频道获取新闻数据
     * https://way.jd.com/jisuapi/get?channel=头条&num=10&start=0&appkey=您申请的APPKEY
     */
    @POST("jisuapi/get")
    Observable<News> getNews(@Query("channel") String channel, @Query("num") String num, @Query("start") String start, @Query("appkey") String appkey);


    /**
     * 京东万象 获取新闻频道
     * https://way.jd.com/jisuapi/channel?appkey=您申请的APPKEY
     */
    @POST("jisuapi/channel")
    Observable<NewsChannel> getNewsChannel(@Query("appkey") String appkey);


    /**
     * 京东万象  关键词查询新闻
     * https://way.jd.com/jisuapi/newSearch?keyword=爱丽丝&appkey=您申请的APPKEY
     */
    @POST("jisuapi/newSearch")
    //                                这里的字段之前没换为appkey  导致接口无法调用！！！
    Observable<NewsSearch> getSearchNews(@Query("keyword") String keyword, @Query("appkey") String appkey);


    /**
     * 京东万象  查询热词排行   限3000次/天
     * https://way.jd.com/showapi/rcInfo?typeId=1&appkey=您申请的APPKEY
     * "id":"1","name":"实时热点"
     */
    @POST("showapi/rcInfo")
    Observable<HotSpot> getHotSpotRanking(@Query("typeId") String typeId, @Query("appkey") String appkey);


    /**
     * 京东万象  查询热点词分类   限3000次/天
     * https://way.jd.com/showapi/rdType?appkey=您申请的APPKEY
     * https://way.jd.com/showapi/rdType?appkey=bd1ee420d53dcd93f21d338cd6bebba3
     */
    @POST("showapi/rdType")
    Observable<NewsCategory> getNewsCategory(@Query("appkey") String appkey);



}

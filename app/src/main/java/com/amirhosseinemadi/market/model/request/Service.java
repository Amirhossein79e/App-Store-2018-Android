package com.amirhosseinemadi.market.model.request;

import com.amirhosseinemadi.market.model.entity.App;
import com.amirhosseinemadi.market.model.entity.Category;
import com.amirhosseinemadi.market.model.entity.Comment;
import com.amirhosseinemadi.market.model.entity.Home;
import java.util.List;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

public interface Service {


    @POST("exe.php")
    @FormUrlEncoded
    Observable<List<Category>> getCategory(@Field("method")String method);


    @POST("exe.php")
    @FormUrlEncoded
    Observable<List<App>> getApp(@Field("method")String method);


    @POST("exe.php")
    @FormUrlEncoded
    Observable<List<App>> getAppByCat(@Field("method")String method,@Field("category")String category);


    @POST("exe.php")
    @FormUrlEncoded
    Observable<ResponseBody> addUser(@Field("method")String method,@Field("username")String username,@Field("email")String email,@Field("password")String password);


    @POST("exe.php")
    @FormUrlEncoded
    Observable<ResponseBody> getUser(@Field("method")String method,@Field("email")String email,@Field("password")String password);


    @POST("exe.php")
    @FormUrlEncoded
    Observable<List<App>> searchApp(@Field("method")String method,@Field("name")String name);


    @POST("exe.php")
    @FormUrlEncoded
    Observable<List<Home>> getHome(@Field("method")String method);


    @POST("exe.php")
    @FormUrlEncoded
    Observable<ResponseBody> addComment(@Field("method")String method,@Field("name")String appName,@Field("username")String username,@Field("stars")int stars,@Field("comment")String comment);


    @POST("exe.php")
    @FormUrlEncoded
    Observable<List<Comment>> getComment(@Field("method")String method,@Field("name")String appName);


    @POST("exe.php")
    @FormUrlEncoded
    Observable<Float> getStars(@Field("method")String method,@Field("name")String appName);


    @POST("app/download.php")
    @Streaming
    @FormUrlEncoded
    Observable<ResponseBody> downloadApp(@Field("name")String name);


    @POST("app/download.php")
    @Streaming
    @FormUrlEncoded
    Call<ResponseBody> downloadAppC(@Field("name")String name);

}

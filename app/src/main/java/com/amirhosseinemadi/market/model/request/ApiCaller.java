package com.amirhosseinemadi.market.model.request;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.common.GetUriProvider;
import com.amirhosseinemadi.market.model.entity.App;
import com.amirhosseinemadi.market.model.entity.Category;
import com.amirhosseinemadi.market.model.entity.Comment;
import com.amirhosseinemadi.market.model.entity.Home;
import com.amirhosseinemadi.market.view.AppView;

import java.io.File;
import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class ApiCaller {

    private Service service;

    public ApiCaller()
    {
        service = MyRetrofit.getRetrofit().create(Service.class);
    }




    public void getCategory(final ResponseListener responseListener)
    {
        Observable<List<Category>> getCategory = service.getCategory("getCategory");
        getCategory.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<Category>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Category> categories) {

                        responseListener.onResponse(categories);
                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    public void getApp(final ResponseListener responseListener)
    {
        Observable<List<App>> getApp = service.getApp("getApp");
        getApp.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<App>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<App> apps) {

                        responseListener.onResponse(apps);
                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    public void getAppByCat(final ResponseListener responseListener,String category)
    {
        Observable<List<App>> getAppByCat = service.getAppByCat("getAppByCat",category);
        getAppByCat.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<App>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<App> apps) {

                        responseListener.onResponse(apps);
                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    public void addUser(final ResponseListener responseListener, String username, String email, String password)
    {
        Observable<ResponseBody> addUser = service.addUser("addUser",username,email,password);
        addUser.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            responseListener.onResponse(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public void getUser(final ResponseListener responseListener, String email, String password)
    {
        Observable<ResponseBody> getUser = service.getUser("getUser",email,password);
        getUser.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            responseListener.onResponse(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    public void searchApp(final ResponseListener responseListener, String name)
    {
        Observable<List<App>> searchApp = service.searchApp("searchApp",name);
        searchApp.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<App>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<App> apps) {

                        responseListener.onResponse(apps);
                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    public void getHome(final ResponseListener responseListener)
    {
        Observable<List<Home>> getHome = service.getHome("getHome");
        getHome.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<Home>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Home> homes) {

                        responseListener.onResponse(homes);
                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void addComment(ResponseListener responseListener,String appName,String username,int stars,String comment)
    {
        Observable<ResponseBody> addComment = service.addComment("addComment",appName,username,stars,comment);
        addComment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        responseListener.onResponse(responseBody);

                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getComment(ResponseListener responseListener,String appName)
    {
        Observable<List<Comment>> getComment = service.getComment("getComment",appName);
        getComment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<Comment>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Comment> list) {

                        responseListener.onResponse(list);

                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getStars(ResponseListener responseListener,String appName)
    {
        Observable<Float> getStars = service.getStars("getStars",appName);
        getStars.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<Float>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Float aFloat) {

                        responseListener.onResponse(aFloat);
                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public void downloadApp(ResponseListener responseListener, String name, AppView appView,String packageName,File file)
    {
        Observable<ResponseBody> downloadApp = service.downloadApp(name);
        downloadApp.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        appView.showProgressBar();

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        responseListener.onResponse(responseBody);

                    }

                    @Override
                    public void onError(Throwable e) {

                        responseListener.onFailed(e.getMessage().toString());

                    }

                    @Override
                    public void onComplete() {

                        appView.hideProgressBar();

                    }
                });
    }



    public void downloadAppC(ResponseListener responseListener,String name)
    {
        Call<ResponseBody> download = service.downloadAppC(name);
        download.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                responseListener.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                responseListener.onFailed(t.getMessage().toString());

            }
        });
    }


    public boolean appInstalled(String packageN)
    {
        PackageManager packageManager = AppCls.component.context().getPackageManager();
        try {
            packageManager.getPackageInfo(packageN,PackageManager.GET_ACTIVITIES);
            return true;
        }catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }


}

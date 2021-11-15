package com.amirhosseinemadi.market.model.request;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    private MyRetrofit(){}

    public static final String BASE_URL = "https://bermodaco.ir/market/";
    public static final String SLIDER_URL = "https://bermodaco.ir/market/slider_image/";
    public static final String APPIC_URL = "https://bermodaco.ir/market/app_icon/";
    public static final String CATIC_URL = "https://bermodaco.ir/market/category_icon/";
    public static final String APPIMG_URL = "https://bermodaco.ir/market/app_image/";
    public static final String APP_URL = "https://bermodaco.ir/market/app/";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }

}

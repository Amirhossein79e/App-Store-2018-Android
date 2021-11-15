package com.amirhosseinemadi.market.common;

import android.app.Application;

public class AppCls extends Application {

    public static Component component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerComponent.builder()
                .module(new Module(getApplicationContext()))
                .build();



    }
}

package com.amirhosseinemadi.market.common;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.amirhosseinemadi.market.activity.MainActivity;
import com.amirhosseinemadi.market.model.request.ApiCaller;

@dagger.Component(modules = Module.class)
public interface Component {

    Context context();

    ApiCaller apiCaller();

    LoginManager loginManager();

    LangManager langManager();

}

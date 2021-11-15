package com.amirhosseinemadi.market.common;

import android.content.Context;
import com.amirhosseinemadi.market.model.request.ApiCaller;

import javax.inject.Inject;

import dagger.Provides;

@dagger.Module
public class Module {

    private final Context context;


    @Inject
    public Module(Context context)
    {
        this.context = context;
    }



    @Provides
    public Context context()
    {
        return context;
    }



    @Provides
    public ApiCaller apiCaller()
    {
        return new ApiCaller();
    }



    @Provides
    public LoginManager loginManager()
    {
        return new LoginManager();
    }



    @Provides
    public LangManager langManager()
    {
        return new LangManager();
    }


}

package com.amirhosseinemadi.market.common;

import android.content.Context;
import android.content.SharedPreferences;

public class LangManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LangManager()
    {
        sharedPreferences = AppCls.component.context().getSharedPreferences("language", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLang(String language)
    {
        editor.putString("language",language).commit();
    }

    public String getLang()
    {
        return sharedPreferences.getString("language","fa");
    }

}

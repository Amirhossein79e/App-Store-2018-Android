package com.amirhosseinemadi.market.common;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginManager {

   private SharedPreferences sharedPreferences;
   private SharedPreferences.Editor editor;

   public LoginManager()
   {
       sharedPreferences = AppCls.component.context().getSharedPreferences("login", Context.MODE_PRIVATE);
       editor = sharedPreferences.edit();
   }


   public boolean getStatus()
   {
       return sharedPreferences.getBoolean("login",false);
   }


   public void setStatus(boolean b)
   {
       editor.putBoolean("login",b).commit();
   }


   public void setUsername(String username)
   {
       editor.putString("username",username).commit();
   }


   public String getUsername()
   {
       return sharedPreferences.getString("username",null);
   }

}

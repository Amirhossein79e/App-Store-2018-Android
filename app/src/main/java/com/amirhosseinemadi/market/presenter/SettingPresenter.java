package com.amirhosseinemadi.market.presenter;

import android.widget.Toast;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.entity.App;
import com.amirhosseinemadi.market.model.request.ResponseListener;
import com.amirhosseinemadi.market.view.SettingView;


public class SettingPresenter {

    private SettingView settingView;
    private int login = 0;
    private int signUp = 0;

    public SettingPresenter(SettingView settingView)
    {
        this.settingView = settingView;
    }


    public int login(String email,String password)
    {
        settingView.showProgress();
        AppCls.component.apiCaller().getUser(new ResponseListener() {
            @Override
            public void onResponse(Object response) {
                String s = (String) response;
                if (!s.equals("null")&&!s.equals("0"))
                {
                    settingView.loginSnack();
                    settingView.hideProgress();
                    AppCls.component.loginManager().setStatus(true);
                    AppCls.component.loginManager().setUsername(s);
                    login = 1;
                    settingView.handleCard();

                }else
                {
                    settingView.failedSnack();
                    settingView.hideProgress();

                }
            }

            @Override
            public void onFailed(String error) {

                settingView.connectionError();
                settingView.hideProgress();


            }
        },email,password);
        return login;
    }


    public int signUp(String username,String email,String password)
    {
        settingView.showProgress();
        AppCls.component.apiCaller().addUser(new ResponseListener() {
            @Override
            public void onResponse(Object response) {
                String s = (String) response;
                int i = Integer.parseInt(s);
                if (i == 1)
                {
                    settingView.signUpSnack();
                    AppCls.component.loginManager().setStatus(true);
                    AppCls.component.loginManager().setUsername(username);
                    signUp = 1;
                    settingView.handleCard();
                    settingView.hideProgress();
                }else
                {
                    settingView.failedSignUp();
                    settingView.hideProgress();
                }
            }

            @Override
            public void onFailed(String error) {

                settingView.hideProgress();
                settingView.connectionError();

            }
        },username,email,password);
        return signUp;
    }


}

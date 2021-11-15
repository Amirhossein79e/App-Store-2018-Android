package com.amirhosseinemadi.market.view;

public interface SettingView {

    void showProgress();

    void hideProgress();

    void loginSnack();

    void failedSnack();

    void signUpSnack();

    void failedSignUp();

    void connectionError();

    void handleCard();

}

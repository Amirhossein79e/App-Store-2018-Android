package com.amirhosseinemadi.market.view;

import com.amirhosseinemadi.market.model.entity.Comment;

import java.util.List;

public interface AppView {

    void initLayout(List<String> slider, List<Comment> comments);

    void showProgress();

    void hideProgress();

    void initRate(Float rate);

    void connectionError();

    void showProgressBar();

    void hideProgressBar();

    void handleProgress(Integer progress);

    void installApp();

    void comment();

}

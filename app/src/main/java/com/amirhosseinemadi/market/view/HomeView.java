package com.amirhosseinemadi.market.view;

import com.amirhosseinemadi.market.model.adapter.HomeAdapter;
import com.amirhosseinemadi.market.model.adapter.SliderAdapter;
import com.amirhosseinemadi.market.model.entity.Home;

import java.util.List;

public interface HomeView {


    void initSlider();

    void initHome(List<Home> list);

    void showProgressbar();

    void hideProgressBar();

    void connectionError();

}

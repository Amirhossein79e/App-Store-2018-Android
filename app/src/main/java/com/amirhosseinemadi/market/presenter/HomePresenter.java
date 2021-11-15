package com.amirhosseinemadi.market.presenter;

import androidx.appcompat.app.AppCompatActivity;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.adapter.HomeAdapter;
import com.amirhosseinemadi.market.model.adapter.SliderAdapter;
import com.amirhosseinemadi.market.model.entity.Home;
import com.amirhosseinemadi.market.model.request.ResponseListener;
import com.amirhosseinemadi.market.view.HomeView;
import java.util.List;

public class HomePresenter {

    private HomeView homeView;

    public HomePresenter(HomeView homeView)
    {
        this.homeView = homeView;

        homeView.showProgressbar();

        homeView.initSlider();

        AppCls.component.apiCaller().getHome(new ResponseListener() {
            @Override
            public void onResponse(Object response) {

                List<Home> list = (List<Home>) response;

                homeView.initHome(list);

                homeView.hideProgressBar();

            }

            @Override
            public void onFailed(String error) {

                homeView.hideProgressBar();
                homeView.connectionError();
            }
        });

    }


}

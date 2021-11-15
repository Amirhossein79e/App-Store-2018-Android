package com.amirhosseinemadi.market.presenter;

import androidx.appcompat.app.AppCompatActivity;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.adapter.AppCategoryAdapter;
import com.amirhosseinemadi.market.model.entity.App;
import com.amirhosseinemadi.market.model.entity.Category;
import com.amirhosseinemadi.market.model.request.ResponseListener;
import com.amirhosseinemadi.market.view.AppCategoryView;
import java.util.List;

public class AppCategoryPresenter {

    private AppCategoryView appCategoryView;

    public AppCategoryPresenter(AppCategoryView appCategoryView, Category category)
    {
        this.appCategoryView = appCategoryView;
        appCategoryView.showProgressBar();

        AppCls.component.apiCaller().getAppByCat(new ResponseListener() {
            @Override
            public void onResponse(Object response) {

                List<App> list = (List<App>) response;
                appCategoryView.initList(list);
                appCategoryView.hideProgressBar();

            }

            @Override
            public void onFailed(String error) {

                appCategoryView.hideProgressBar();
                appCategoryView.connectionError();

            }
        },category.getName());

    }

}

package com.amirhosseinemadi.market.presenter;

import androidx.appcompat.app.AppCompatActivity;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.adapter.AppCategoryAdapter;
import com.amirhosseinemadi.market.model.entity.App;
import com.amirhosseinemadi.market.model.request.ResponseListener;
import com.amirhosseinemadi.market.view.SearchView;
import java.util.List;

public class SearchPresenter {

    private SearchView searchView;

    public SearchPresenter(SearchView searchView, String name)
    {
        this.searchView = searchView;

        searchView.showProgressBar();
        AppCls.component.apiCaller().searchApp(new ResponseListener() {
            @Override
            public void onResponse(Object response) {

                List<App> list  = (List<App>) response;
                searchView.initList(list);
                searchView.hideProgressBar();
            }

            @Override
            public void onFailed(String error) {

                searchView.hideProgressBar();
                searchView.connectionError();

            }
        },name);

    }

}

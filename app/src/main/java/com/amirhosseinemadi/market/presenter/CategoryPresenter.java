package com.amirhosseinemadi.market.presenter;

import androidx.appcompat.app.AppCompatActivity;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.entity.Category;
import com.amirhosseinemadi.market.model.request.ResponseListener;
import com.amirhosseinemadi.market.view.CategoryView;
import java.util.List;

public class CategoryPresenter {

    private CategoryView categoryView;

    public CategoryPresenter(CategoryView categoryView, AppCompatActivity activity)
    {
        this.categoryView = categoryView;

        categoryView.showProgressBar();

        AppCls.component.apiCaller().getCategory(new ResponseListener() {
            @Override
            public void onResponse(Object response) {

                List<Category> list = (List<Category>) response;
                categoryView.initCategory(list);
                categoryView.hideProgressBar();
            }

            @Override
            public void onFailed(String error) {

                categoryView.hideProgressBar();
                categoryView.connectionError();

            }
        });

    }

}

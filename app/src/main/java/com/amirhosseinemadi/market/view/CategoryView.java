package com.amirhosseinemadi.market.view;

import com.amirhosseinemadi.market.model.entity.Category;
import java.util.List;

public interface CategoryView {

    void initCategory(List<Category> list);

    void showProgressBar();

    void hideProgressBar();

    void connectionError();

}

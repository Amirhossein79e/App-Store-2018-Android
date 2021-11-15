package com.amirhosseinemadi.market.view;

import com.amirhosseinemadi.market.model.entity.App;
import java.util.List;

public interface SearchView {

    void initList(List<App> list);

    void showProgressBar();

    void hideProgressBar();

    void connectionError();

}

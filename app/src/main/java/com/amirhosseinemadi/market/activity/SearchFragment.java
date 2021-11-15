package com.amirhosseinemadi.market.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.adapter.AppCategoryAdapter;
import com.amirhosseinemadi.market.model.entity.App;
import com.amirhosseinemadi.market.presenter.SearchPresenter;
import com.amirhosseinemadi.market.view.SearchView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SearchPresenter searchPresenter;
    private AppCompatTextView noAppSearch;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Bundle bundle = this.getArguments();
        recyclerView = view.findViewById(R.id.search_recycler);
        progressBar = view.findViewById(R.id.search_progress);
        noAppSearch = view.findViewById(R.id.txt_no_search);

        searchPresenter = new SearchPresenter(SearchFragment.this,bundle.getString("query"));

        return view;
    }

    @Override
    public void initList(List<App> list) {

        AppCategoryAdapter appCategoryAdapter = new AppCategoryAdapter(list, (AppCompatActivity) getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(AppCls.component.context(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(appCategoryAdapter);
        if (list.size() == 0)
        {
            noAppSearch.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void connectionError() {

        Snackbar.make(getView(),R.string.ConnectionFailed, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.bottom_app_bar).show();

    }
}

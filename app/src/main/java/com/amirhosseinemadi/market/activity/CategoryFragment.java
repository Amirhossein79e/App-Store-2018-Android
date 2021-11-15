package com.amirhosseinemadi.market.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.adapter.CategoryAdapter;
import com.amirhosseinemadi.market.model.entity.Category;
import com.amirhosseinemadi.market.presenter.CategoryPresenter;
import com.amirhosseinemadi.market.view.CategoryView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CategoryView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = view.findViewById(R.id.recycler_category);

        progressBar = view.findViewById(R.id.category_progress);

        CategoryPresenter categoryPresenter = new CategoryPresenter(CategoryFragment.this, (AppCompatActivity) getActivity());

        return view;
}

    @Override
    public void initCategory(List<Category> list) {

        CategoryAdapter categoryAdapter = new CategoryAdapter(list, (AppCompatActivity) getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(AppCls.component.context(),RecyclerView.VERTICAL,false);

        recyclerView.setAdapter(categoryAdapter);

        recyclerView.setLayoutManager(layoutManager);

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

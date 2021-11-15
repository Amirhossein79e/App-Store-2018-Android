package com.amirhosseinemadi.market.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.adapter.HomeAdapter;
import com.amirhosseinemadi.market.model.adapter.SliderAdapter;
import com.amirhosseinemadi.market.model.entity.Home;
import com.amirhosseinemadi.market.presenter.HomePresenter;
import com.amirhosseinemadi.market.view.HomeView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.viewPager);

        recyclerView = view.findViewById(R.id.recycler_home);

        progressBar = view.findViewById(R.id.home_progress);

        tabLayout = view.findViewById(R.id.tab_lay);

        tabLayout.setupWithViewPager(viewPager);

        HomePresenter homePresenter = new HomePresenter(HomeFragment.this);

        return view;

    }


    @Override
    public void initSlider() {

        SliderAdapter sliderAdapter = new SliderAdapter();
        viewPager.setAdapter(sliderAdapter);

    }

    @Override
    public void initHome(List<Home> list) {

        HomeAdapter homeAdapter = new HomeAdapter(list, (AppCompatActivity) getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(AppCls.component.context(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);

    }

    @Override
    public void showProgressbar() {

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

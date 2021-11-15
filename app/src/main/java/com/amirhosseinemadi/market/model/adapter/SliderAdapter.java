package com.amirhosseinemadi.market.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;
import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.request.MyRetrofit;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<String> list;

    public SliderAdapter()
    {
        list = new ArrayList<>();
        list.add("1.jpg");
        list.add("2.jpg");
        list.add("3.jpg");
    }




    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = LayoutInflater.from(AppCls.component.context());
        View view = layoutInflater.inflate(R.layout.slider_row,null);

        AppCompatImageView imageView = view.findViewById(R.id.slider_img);

        String s = list.get(position);

        Picasso.get().load(MyRetrofit.SLIDER_URL+s).into(imageView);

        container.addView(view);

        return view;

    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}

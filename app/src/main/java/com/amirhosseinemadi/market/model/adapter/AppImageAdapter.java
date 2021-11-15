package com.amirhosseinemadi.market.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.request.MyRetrofit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AppImageAdapter extends RecyclerView.Adapter<AppImageAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> list;
    private String name;

    public AppImageAdapter(List<String> list,String name)
    {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(AppCls.component.context());
        this.name = name;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.app_image_row,null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String s = list.get(position);

        Picasso.get().load(MyRetrofit.APPIMG_URL+name+"/"+s+".jpg").into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.app_image_item);
        }
    }
}

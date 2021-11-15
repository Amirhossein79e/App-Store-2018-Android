package com.amirhosseinemadi.market.model.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.activity.AppFragment;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.entity.App;
import com.amirhosseinemadi.market.model.request.MyRetrofit;
import com.squareup.picasso.Picasso;
import java.util.List;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.ViewHolder>{

    private List<App> list;
    private LayoutInflater layoutInflater;
    private AppCompatActivity activity;

    public SubAdapter(List<App> list, AppCompatActivity activity)
    {
        this.activity = activity;
        this.list = list;
        layoutInflater = LayoutInflater.from(AppCls.component.context());
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.home_sub_row,null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        App app = list.get(position);

        holder.textView.setText(app.getName());

        Picasso.get().load(MyRetrofit.APPIC_URL+app.getIcon()+".png").into(holder.imageView);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppFragment appFragment = new AppFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("app",app);
                appFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,appFragment,null).addToBackStack("app").commit();

            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imageView;
        AppCompatTextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.home_sub_image);
            textView = itemView.findViewById(R.id.home_sub_text);
            relativeLayout = itemView.findViewById(R.id.sub_layout);
        }
    }
}

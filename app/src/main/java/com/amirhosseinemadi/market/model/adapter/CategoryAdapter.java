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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.activity.AppCategoryFragment;
import com.amirhosseinemadi.market.activity.MainActivity;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.entity.Category;
import com.amirhosseinemadi.market.model.request.MyRetrofit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private List<Category> list;
    private LayoutInflater layoutInflater;
    AppCompatActivity activity;

    public CategoryAdapter(List<Category> list,AppCompatActivity activity)
    {
        this.activity = activity;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(AppCls.component.context());
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.category_row,null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Category category = list.get(position);

        holder.textView.setText(category.getName());

        Picasso.get().load(MyRetrofit.CATIC_URL+category.getIcon()+".png").into(holder.imageView);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCategoryFragment appCategoryFragment = new AppCategoryFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("category",category);
                appCategoryFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,appCategoryFragment,null).addToBackStack("backCat").commit();

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
            imageView = itemView.findViewById(R.id.category_image);
            textView = itemView.findViewById(R.id.category_text);
            relativeLayout = itemView.findViewById(R.id.category_lay);
        }
    }
}

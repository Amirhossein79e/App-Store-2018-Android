package com.amirhosseinemadi.market.model.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.entity.App;
import com.amirhosseinemadi.market.model.entity.Home;
import com.amirhosseinemadi.market.model.request.ResponseListener;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    private List<Home> list;
    private List<App> list2;
    private LayoutInflater layoutInflater;
    private AppCompatActivity activity;

    public HomeAdapter(List<Home> list,AppCompatActivity activity)
    {
        this.activity = activity;
        this.list = list;
        layoutInflater = LayoutInflater.from(AppCls.component.context());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.home_row,null);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Home home = list.get(position);

        holder.textView.setText(home.getTitle());

        AppCls.component.apiCaller().getAppByCat(new ResponseListener() {
            @Override
            public void onResponse(Object response) {

                list2 = (List<App>) response;

                Log.e("","");

                SubAdapter subAdapter = new SubAdapter(list2,activity);

                LinearLayoutManager layoutManager = new LinearLayoutManager(AppCls.component.context(),RecyclerView.HORIZONTAL,false);

                holder.recyclerView.setLayoutManager(layoutManager);

                holder.recyclerView.setAdapter(subAdapter);


            }

            @Override
            public void onFailed(String error) {

                System.out.println(error);

            }
        },home.getCategory());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView textView;
        CardView cardView;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.homeTxt);
            cardView = itemView.findViewById(R.id.homeCard);
            recyclerView = itemView.findViewById(R.id.home_recycler);
        }
    }

}

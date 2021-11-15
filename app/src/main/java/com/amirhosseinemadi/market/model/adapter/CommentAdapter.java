package com.amirhosseinemadi.market.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.entity.Comment;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    private List<Comment> list;
    private LayoutInflater layoutInflater;

    public CommentAdapter(List<Comment> list)
    {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(AppCls.component.context());
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.comment_row,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Comment comment = list.get(position);
        holder.username.setText(comment.getUsername());
        holder.text.setText(comment.getComment());
        holder.commentRate.setText(String.valueOf(comment.getStars()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView username,text,commentRate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.comment_user);
            text = itemView.findViewById(R.id.comment_text);
            commentRate = itemView.findViewById(R.id.comment_rate);
        }
    }
}

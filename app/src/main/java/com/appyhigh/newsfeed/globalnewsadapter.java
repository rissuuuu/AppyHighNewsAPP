package com.appyhigh.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class globalnewsadapter extends RecyclerView.Adapter<globalnewsadapter.ViewHolder> {
     Context mContext;
    LayoutInflater inflater;
    List<News> news;


    public globalnewsadapter(Context ctx, List<News> news) {
        this.mContext=ctx;
        this.inflater = LayoutInflater.from(ctx);
        this.news = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.display_newscard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.texttitle.setText(news.get(position).getTitle());
        holder.description.setText(news.get(position).getDescription());
        Picasso.get().load(news.get(position).getUrlToImage()).resize(500,400).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,WebV.class);
                intent.putExtra("webview",news.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size()  ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView texttitle;
        ImageView imageView;
        TextView description;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            texttitle = itemView.findViewById(R.id.id_title);
            imageView = itemView.findViewById(R.id.id_image);
            description = itemView.findViewById(R.id.id_description);
            cardView=itemView.findViewById(R.id.click);
        }
    }
}

package com.leo.catalogmanga.Data;

import static java.lang.System.load;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leo.catalogmanga.Model.Manga;
import com.leo.catalogmanga.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MangaRecyclerViewAdapter extends RecyclerView.Adapter<MangaRecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<Manga> mangaList;

    @NonNull
    @Override
    public MangaRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manga,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MangaRecyclerViewAdapter.ViewHolder holder, int position)
    {
        Manga manga = mangaList.get(position);
        String mangaCover=manga.getMangaCover();
        holder.mangaTitle.setText(manga.getMangaTitle());
        holder.mangaScrapeDate.setText(manga.getScrapeDate());
        holder.type.setText(manga.getType());
        Picasso.get()
                .load(mangaCover)
                .fit()
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.mangaCover);
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mangaTitle;
        ImageView mangaCover;
        TextView mangaScrapeDate;
        TextView type;

        public ViewHolder(@NonNull View itemView, Context context)
        {
            super(itemView);
            context=context;
            mangaTitle=itemView.findViewById(R.id.mangaTitleID);
            mangaCover=itemView.findViewById(R.id.mangaImageID);
            mangaScrapeDate=itemView.findViewById(R.id.mangaScrapeDateID);
            type=itemView.findViewById(R.id.mangaCatID);

        }
    }
}

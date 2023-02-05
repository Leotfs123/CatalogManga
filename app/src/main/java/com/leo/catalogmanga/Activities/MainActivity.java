package com.leo.catalogmanga.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leo.catalogmanga.Data.MangaRecyclerViewAdapter;
import com.leo.catalogmanga.Model.Manga;
import com.leo.catalogmanga.R;
import com.leo.catalogmanga.Util.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private  RecyclerView recyclerView;
    private MangaRecyclerViewAdapter mangaRecyclerViewAdapter;
    private List <Manga> MangaList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(this);

        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MangaList=new ArrayList<>();

        Prefs prefs=new Prefs(MainActivity.this);
        String search=prefs.getSearch();
        MangaList=getManga(search);
        mangaRecyclerViewAdapter=new MangaRecyclerViewAdapter(this,MangaList);
        recyclerView.setAdapter(mangaRecyclerViewAdapter);
    }


    public List<Manga> getManga (String searchTerm)
    {
        MangaList.clear();
        //String myUrl="https://manga-scrapper.p.rapidapi.com/?s=" + searchTerm + "r=json";
        String myUrl="https://manga-scrapper.p.rapidapi.com/search/" + searchTerm + "/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(myUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject mangaObj=response.getJSONObject("data");
                   // Log.d("TAG","message1= "+mangaObj.toString());
                    JSONArray mangaArray=mangaObj.getJSONArray("result");
                    for (int i=0;i<mangaArray.length();i++)
                    {
                        JSONObject mangaOb=mangaArray.getJSONObject(i);
                        Manga manga=new Manga();
                        manga.setMangaTitle(mangaOb.getString("MangaTitle"));
                        manga.setScrapeDate(mangaOb.getString("ScrapeDate"));
                        manga.setMangaSynopsis(mangaOb.getString("MangaSynopsis"));
                        manga.setMangaCover(mangaOb.getString("MangaCover"));
                        manga.setType(mangaOb.getString("_type"));
                        MangaList.add(manga);
                    }
                    mangaRecyclerViewAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String> params=new HashMap<>();
                params.put("X-RapidAPI-Host", "manga-scrapper.p.rapidapi.com");
                params.put("X-RapidAPI-Key", "95d38d296bmsh507db856ce91a7dp1553f3jsnc0119e6c6892");
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
        return MangaList;
    }
}
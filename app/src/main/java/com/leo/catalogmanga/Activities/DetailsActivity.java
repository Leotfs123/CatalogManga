package com.leo.catalogmanga.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leo.catalogmanga.Model.Manga;
import com.leo.catalogmanga.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
    private Manga manga;
    private TextView MangaTitleID_details, MangaScrapeDateID_details, MangaTypeID_details;
    private ImageView MangaCoverID_details;
    private RequestQueue queue;
    String mangaID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        queue = Volley.newRequestQueue(this);

        manga = (Manga) getIntent().getSerializableExtra("manga");
        mangaID = manga.getId();
        MangaTitleID_details=findViewById(R.id.MangaTitleID_details);
        MangaScrapeDateID_details=findViewById(R.id.MangaScrapeDateID);
        MangaTypeID_details=findViewById(R.id.MangaTypeID);
        MangaCoverID_details=findViewById(R.id.MangaCoverID_details);

        getMangaDetails(mangaID);




    }

    private void getMangaDetails(String id)
    {
        String myUrl="https://manga-scrapper.p.rapidapi.com/series/?provider=asura" + id;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(myUrl, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        MangaTitleID_details.setText(response.getString("Title"));
                        MangaScrapeDateID_details.setText("Released" + response.getString ("Released"));
                        MangaTypeID_details.setText("Type: " + response.getString("Type"));


                        Picasso.get()
                                .load(response.getString("Manga Cover"))
                                .fit()
                                .into(MangaCoverID_details);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Erreur", "Err" + error);
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("X-RapidAPI-Host", "manga-scrapper.p.rapidapi.com");
                    params.put("X-RapidAPI-Key", "95d38d296bmsh507db856ce91a7dp1553f3jsnc0119e6c6892");
                    return params;
                }
            };
            ;
            queue.add(jsonObjectRequest);
    }
}

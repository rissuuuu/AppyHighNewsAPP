package com.appyhigh.newsfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupMenu;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Country_news extends AppCompatActivity {
    private RequestQueue mQueue;
    Button settings;
    RecyclerView recyclerView;
    globalnewsadapter globalnewsadapter;
    List<News> addnews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_country_news);
        settings = findViewById(R.id.id_settings);
        recyclerView = findViewById(R.id.recyclerviewcountries);
        mQueue = Volley.newRequestQueue(this);
        addnews = new ArrayList<>();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup(v);
            }
        });
    }


    public void ShowPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.options);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_india:
                        addnews = new ArrayList<>();
                        jsonParse("India");
                        return true;
                    case R.id.id_usa:
                        addnews = new ArrayList<>();
                        jsonParse("us");
                        return true;
                    case R.id.id_germany:
                        addnews = new ArrayList<>();
                        jsonParse("de");
                        return true;
                    case R.id.id_nepal:
                        return true;
                }
                return true;
            }
        });
    }

    private void jsonParse(String country) {
        String url = "http://newsapi.org/v2/top-headlines?country=" + country + "&category=business&apiKey=d041ee0094ae47efa3e078e1ceec2eae";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("articles");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject articles = jsonArray.getJSONObject(i);
                                News news = new News();
                                news.setTitle(articles.getString("title"));
                                news.setDescription(articles.getString("description"));
                                news.setUrlToImage(articles.getString("urlToImage"));
                                news.setUrl(articles.getString("url"));
                                addnews.add(news);
//                                String description=articles.getString("description");
//                                String publishedAt=articles.getString("publishedAt");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        globalnewsadapter = new globalnewsadapter(getApplicationContext(), addnews);
                        recyclerView.setAdapter(globalnewsadapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

}

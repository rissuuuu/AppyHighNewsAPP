package com.appyhigh.newsfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Global extends AppCompatActivity {
    private RequestQueue mQueue;
    TextView fetch;
    RecyclerView recyclerView;
    List<News> addnews;
    globalnewsadapter globalnewsadapter;
    AdView adView,mAdview;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_global);
        mAdview = findViewById(R.id.ad_view);
        mAdview.bringToFront();
        recyclerView = findViewById(R.id.recyclerviewglobal);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        addnews = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        jsonParse("us");
//        interstitialAd.show();
    }

    private void jsonParse(String country) {
        String[] url = {"http://newsapi.org/v2/top-headlines?country=" + country + "&category=business&apiKey=d041ee0094ae47efa3e078e1ceec2eae", "" +
                "http://newsapi.org/v2/everything?q=bitcoin&from=2020-05-18&sortBy=publishedAt&apiKey=d041ee0094ae47efa3e078e1ceec2eae"};
        for (int i = 0; i < url.length; i++) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url[i], null,
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
}

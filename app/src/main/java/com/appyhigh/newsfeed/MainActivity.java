package com.appyhigh.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    int time=2000;

    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mQueue= Volley.newRequestQueue(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,Login.class));

            }
        },time);


//        get.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                jsonParse("us");
//
//
//            }
//        });
    }
//    private void jsonParse(String country){
//        String url="http://newsapi.org/v2/top-headlines?country="+country+"&category=business&apiKey=d041ee0094ae47efa3e078e1ceec2eae";
//        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray=response.getJSONArray("articles");
//                            for(int i=0;i<jsonArray.length();i++){
//                                JSONObject articles=jsonArray.getJSONObject(i);
//                                String author=articles.getString("author");
//                                String title=articles.getString("title");
////                                String description=articles.getString("description");
////                                String publishedAt=articles.getString("publishedAt");
//                                fetch.append(author+"\n"+title+"\n\n_______________________________\n");
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//    mQueue.add(request);
//    }
}

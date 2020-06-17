package com.appyhigh.newsfeed;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonParserVolley{
    final String contentType = "application/json; charset=utf-8";
    String JsonURL = "Your URL";
    Context context;
    RequestQueue requestQueue;
    String jsonresponse;

    private Map<String, String> header;

    public JsonParserVolley(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
        header = new HashMap<>();

    }

    public void addHeader(String key, String value) {
        header.put(key, value);
    }

    public void executeRequest(int method, final VolleyCallback callback) {

        StringRequest stringRequest = new StringRequest(method, JsonURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonresponse = response;
                Log.e("RES", " res::" + jsonresponse);
                callback.getResponse(jsonresponse);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return header;
            }
        }
                ;
        requestQueue.add(stringRequest);

    }

    public interface VolleyCallback
    {
        public void getResponse(String response);
    }
    }


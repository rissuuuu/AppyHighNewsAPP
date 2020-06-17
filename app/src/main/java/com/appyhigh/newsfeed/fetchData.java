package com.appyhigh.newsfeed;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data="";
    String dataparsed="";
    String singleparsed="";


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url=new URL("http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=d041ee0094ae47efa3e078e1ceec2eae");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while ( line!=null){
                line=bufferedReader.readLine();
                data=data+line;
            }
            JSONArray jsonArray=new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                singleparsed= (String) jsonObject.get("articles");
//                        "articles"+jsonObject.get("articles")+"\n";
                dataparsed=dataparsed+singleparsed+"\n";
                Log.i("Display",this.dataparsed);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
//        MainActivity.fetch.setText(this.dataparsed);
        super.onPostExecute(aVoid);
//        Log.i("Display",this.dataparsed);

    }
}

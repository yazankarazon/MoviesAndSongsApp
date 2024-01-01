package com.example.moviesandsongsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SongsActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RequestQueue queue;
    private List<MediaItem> ArraySongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);


        rv = findViewById(R.id.RV_Songs);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ArraySongs = new ArrayList<>();
        queue = Volley.newRequestQueue(this);


        ListData();


    }


    private void ListData(){

        String url = "https://raw.githubusercontent.com/ShivamJoker/sample-songs/master/data.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0; i<= response.length(); i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);

                        String artwork = jsonObject.getString("artwork");
                        String title = jsonObject.getString("title");
                        String artist= jsonObject.getString("artist");

                        MediaItem Song = new MediaItem(artwork, title, artist);
                        ArraySongs.add(Song);

                    }catch(JSONException exception){
                        Log.d("volley_error", exception.toString());
                    }

                    MediaRecyclerViewAdapter adapter = new MediaRecyclerViewAdapter(SongsActivity.this, ArraySongs);
                    rv.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        queue.add(jsonArrayRequest);

    }
}
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RequestQueue queue;
    private List<MediaItem> ArrayMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);


        rv = findViewById(R.id.RV_Movies);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ArrayMovies = new ArrayList<>();
        queue = Volley.newRequestQueue(this);



        ListData();


    }


    private void ListData(){

        String url = "https://run.mocky.io/v3/117ded4f-cc18-4df7-818d-71f3c6026e55";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0; i<= response.length(); i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        String image = jsonObject.getString("image");
                        String title = jsonObject.getString("name");
                        String description= jsonObject.getString("description");

                        MediaItem Movie = new MediaItem(image, title, description);
                        ArrayMovies.add(Movie);

                    }catch(JSONException exception){
                        Log.d("volley_error", exception.toString());
                    }

                    MediaRecyclerViewAdapter adapter = new MediaRecyclerViewAdapter(MoviesActivity.this, ArrayMovies);
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
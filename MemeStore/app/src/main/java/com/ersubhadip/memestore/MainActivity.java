package com.ersubhadip.memestore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.squareup.picasso.BuildConfig;
import com.squareup.picasso.Picasso;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    private Button share , next;
    private ImageView img;
    private ProgressBar prog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.image);
        share=findViewById(R.id.shareBtn);
        next=findViewById(R.id.nextBtn);
        prog=findViewById(R.id.progress);

        loadMeme();


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //todo:share intent
                //todo:Lock Orientation

                Toast.makeText(MainActivity.this, "Under Development", Toast.LENGTH_SHORT).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextMeme();
            }
        });


    }

    private void loadMeme() {

        prog.setVisibility(View.VISIBLE);
        img.setImageDrawable(null);



        //implementing volley
        String url = "https://meme-api.herokuapp.com/gimme";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String url = response.getString("url");

                            prog.setVisibility(View.INVISIBLE);


                           Glide.with(MainActivity.this).load(url).into(img);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Something Went Wrong! Check Your Internet Connection !", Toast.LENGTH_LONG).show();

                    }
                });


        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


    }


    private void nextMeme(){

        loadMeme();
    }


}
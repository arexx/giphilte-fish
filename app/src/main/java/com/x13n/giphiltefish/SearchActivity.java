package com.x13n.giphiltefish;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.x13n.giphiltefish.net.NetworkManager;
import com.x13n.giphiltefish.net.giphy.model.GiphyImage;
import com.x13n.giphiltefish.net.giphy.model.GiphyResponse;
import com.x13n.giphiltefish.net.giphy.TrendingRequest;

public class SearchActivity extends Activity {
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Request trendingRequest = new TrendingRequest(new Response.Listener<GiphyResponse>() {
            @Override
            public void onResponse(GiphyResponse response) {
                Log.i(TAG, "Response: " + response);

                for (GiphyImage image : response.getImages()) {
                    Log.d(TAG, "Image: " + image.getUrl());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle errors.
                Log.e(TAG, "Unhandled error! " + error);
            }
        });

        NetworkManager.getInstance(this).addToRequestQueue(trendingRequest);
    }

}

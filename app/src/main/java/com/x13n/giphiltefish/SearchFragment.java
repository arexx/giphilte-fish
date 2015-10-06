package com.x13n.giphiltefish;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.x13n.giphiltefish.models.RecyclerItemListener;
import com.x13n.giphiltefish.net.NetworkManager;
import com.x13n.giphiltefish.net.giphy.TrendingRequest;
import com.x13n.giphiltefish.net.giphy.model.GiphyImage;
import com.x13n.giphiltefish.net.giphy.model.GiphyResponse;

/**
 * An EditText for a search term, and a list of search results.
 *
 * Created by alex on 05/10/15.
 */
public class SearchFragment extends Fragment implements RecyclerItemListener {
    private static final String TAG = "SearchFragment";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SearchAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start loading recent GIFs from the server.
        loadRecent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_search, container, false);

        mRecyclerView = (RecyclerView) layout;

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SearchAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        return layout;
    }

    private void loadRecent() {
        Request trendingRequest = new TrendingRequest(new Response.Listener<GiphyResponse>() {
            @Override
            public void onResponse(GiphyResponse response) {
                Log.i(TAG, "Response: " + response);

                for (GiphyImage image : response.getImages()) {
                    Log.d(TAG, "Image: " + image.getUrl());
                }

                // TODO: Verify that view has been created.
                mAdapter.showResults("Trending", response.getImages());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle errors.
                Log.e(TAG, "Unhandled error! " + error);
            }
        });

        NetworkManager.getInstance(getActivity()).addToRequestQueue(trendingRequest);
    }

    @Override
    public void onSearchTermEntered(String searchTerm) {
        Log.i(TAG, "Search term entered: " + searchTerm);
    }

    @Override
    public void onImageTouched(GiphyImage image) {
        Log.i(TAG, "Image touched: " + image.getUrl());
    }
}

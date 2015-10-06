package com.x13n.giphiltefish.fragments;

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
import com.x13n.giphiltefish.R;
import com.x13n.giphiltefish.helpers.StubHelper;
import com.x13n.giphiltefish.models.RecyclerItemListener;
import com.x13n.giphiltefish.net.NetworkManager;
import com.x13n.giphiltefish.net.giphy.SearchRequest;
import com.x13n.giphiltefish.net.giphy.TrendingRequest;
import com.x13n.giphiltefish.net.giphy.model.GiphyImage;
import com.x13n.giphiltefish.net.giphy.model.GiphyResponse;

import java.util.ArrayList;

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
    private OnImageSelectedListener mCallback;

    public interface OnImageSelectedListener {
        void onImageSelected(GiphyImage image);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mCallback = (OnImageSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement OnImageSelectedListener");
        }

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
                // TODO: Verify that view has been created.
                mAdapter.showResults("Trending", response.getImages());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Unhandled error! " + error);
                StubHelper.showYouBrokeItDialog(
                        "Couldn't load search results. Are you connected to the internet?",
                        new Runnable() {
                            @Override
                            public void run() {
                                loadRecent();
                            }
                        },
                        getActivity());
            }
        });

        NetworkManager.getInstance(getActivity()).addToRequestQueue(trendingRequest);
    }

    private void loadSearchResults(final String searchTerm) {
        Request searchRequest = new SearchRequest(searchTerm, new Response.Listener<GiphyResponse>() {
            @Override
            public void onResponse(GiphyResponse response) {
                // TODO: Verify that view has been created.
                mAdapter.showResults("Results for “" + searchTerm + "”", response.getImages());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Unhandled error! " + error);
                StubHelper.showYouBrokeItDialog(
                        "Couldn't load search results. Are you connected to the internet?",
                        new Runnable() {
                            @Override
                            public void run() {
                                loadSearchResults(searchTerm);
                            }
                        },
                        getActivity());
            }
        });

        NetworkManager.getInstance(getActivity()).addToRequestQueue(searchRequest);
    }

    @Override
    public void onSearchTermEntered(String searchTerm) {
        Log.i(TAG, "Search term entered: " + searchTerm);

        // Clear the currently visible results.
        mAdapter.showResults("Searching...", new ArrayList<GiphyImage>());

        loadSearchResults(searchTerm);
    }

    @Override
    public void onImageTouched(GiphyImage image) {
        Log.i(TAG, "Image touched: " + image.getUrl());
        mCallback.onImageSelected(image);
    }
}

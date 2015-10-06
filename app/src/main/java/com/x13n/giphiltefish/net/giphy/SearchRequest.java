package com.x13n.giphiltefish.net.giphy;

import com.android.volley.Response;
import com.x13n.giphiltefish.net.giphy.model.GiphyResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * A request to the Giphy API for trending GIFs
 *
 * Created by alex on 05/10/15.
 */
public class SearchRequest extends GiphyRequest {
    public SearchRequest(String searchTerm, Response.Listener<GiphyResponse> listener, Response.ErrorListener errorListener) {
        super("gifs/search", buildQuery(searchTerm), listener, errorListener);
    }

    private static Map<String, String> buildQuery(String searchTerm) {
        HashMap<String, String> query = new HashMap<>();
        query.put("q", searchTerm);
        return query;
    }
}

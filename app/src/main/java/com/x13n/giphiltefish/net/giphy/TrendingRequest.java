package com.x13n.giphiltefish.net.giphy;

import com.android.volley.Response;
import com.x13n.giphiltefish.net.giphy.model.GiphyResponse;

/**
 * A request to the Giphy API for trending GIFs
 *
 * Created by alex on 05/10/15.
 */
public class TrendingRequest extends GiphyRequest {
    public TrendingRequest(Response.Listener<GiphyResponse> listener, Response.ErrorListener errorListener) {
        super("gifs/trending", null, listener, errorListener);
    }
}

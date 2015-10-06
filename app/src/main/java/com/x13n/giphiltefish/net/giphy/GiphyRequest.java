package com.x13n.giphiltefish.net.giphy;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.android.volley.Response;
import com.x13n.giphiltefish.Constants;
import com.x13n.giphiltefish.net.GsonRequest;
import com.x13n.giphiltefish.net.giphy.model.GiphyResponse;

import java.util.Map;

/**
 * Base class for requests to the Giphy API
 * Created by alex on 05/10/15.
 */
abstract class GiphyRequest extends GsonRequest<GiphyResponse> {

    /**
     * Make a GET request to the Giphy API.
     *
     * @param path          The request path, to be appended to the Giphy API stub.
     * @param listener      The object to call when the request is successful
     * @param errorListener The object to call when the request fails
     */
    public GiphyRequest(String path, @Nullable Map<String, String> params, Response.Listener<GiphyResponse> listener, Response.ErrorListener errorListener) {
        super(buildUrl(path, params),
                GiphyResponse.class,
                null,
                listener,
                errorListener);
    }

    /**
     * Given a Giphy API path and a dictionary of parameters, assemble the full API URL.
     */
    private static String buildUrl(String path, @Nullable Map<String, String> params) {
        Uri.Builder builder = Constants.GIPHY_API_STUB.buildUpon();
        builder.appendEncodedPath(path);

        // Append each dictionary parameter to the URI in turn
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                builder.appendQueryParameter(param.getKey(), param.getValue());
            }
        }

        // Append the API key
        builder.appendQueryParameter("api_key", Constants.GIPHY_KEY);

        return builder.build().toString();
    }
}

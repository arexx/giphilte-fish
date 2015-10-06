package com.x13n.giphiltefish.net.giphy.model;

import java.util.List;

/**
 * A POJO for responses from the Giphy API. GSON deserializes the
 * server responses into objects with this class.
 *
 * Created by alex on 05/10/15.
 */
public class GiphyResponse {
    List<GiphyImage> data;
    Meta meta;
    Pagination pagination;

    public List<GiphyImage> getImages() {
        return data;
    }
}

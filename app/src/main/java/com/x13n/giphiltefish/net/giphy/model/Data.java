package com.x13n.giphiltefish.net.giphy.model;

import java.util.List;

/**
 *  An envelope for the actual data in a Giphy API response.
 *
 * The object exists for GSON's convenience. Actual access to these properties is via the
 * GiphyResponse object.
 *
 * Created by alex on 05/10/15.
 */
class Data {
    List<GiphyImage> images;
}

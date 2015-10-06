package com.x13n.giphiltefish.net.giphy.model;

/**
 * An object describing a variation of an image on the Giphy service.
 *
 * The object exists for GSON's convenience. Actual access to these properties is via the
 * GiphyResponse object.
 *
 * Created by alex on 05/10/15.
 */
class ImageVariation {
    int width;
    int height;
    int size;
    String url;
}

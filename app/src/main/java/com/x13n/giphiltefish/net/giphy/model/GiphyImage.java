package com.x13n.giphiltefish.net.giphy.model;

import java.util.Map;

/**
 * A data object representing a single image on the Giphy image-hosting service.
 * GSON creates these objects when deserializing responses from the Giphy API.
 *
 * Created by alex on 05/10/15.
 */
public class GiphyImage {
    Map<String, ImageVariation> images;

    private ImageVariation getPreferredVariation() {
        return images.get("fixed_width");
    }

    public String getUrl() {
        return getPreferredVariation().url;
    }

    public int getWidth() {
        return getPreferredVariation().width;
    }

    public int getHeight() {
        return getPreferredVariation().height;
    }

    /**
     * Get the height that the image should have when it is displayed at a given width, to preserve
     * aspect ratio.
     */
    public int getHeight(int width) {
        ImageVariation variation = getPreferredVariation();
        return Math.round(variation.height * (width/(float) variation.width));
    }
}

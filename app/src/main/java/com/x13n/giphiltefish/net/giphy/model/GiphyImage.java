package com.x13n.giphiltefish.net.giphy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * A data object representing a single image on the Giphy image-hosting service.
 * GSON creates these objects when deserializing responses from the Giphy API.
 *
 * The object is Parcelable so that it can be packaged into a bundle and passed
 * to a Fragment. This is in preference to putting it into shared memory and fetching
 * it by key because fragments can effectively outlive the application process when
 * they are restored using persisted bundles.
 *
 * Created by alex on 05/10/15.
 */
public class GiphyImage implements Parcelable {
    Map<String, ImageVariation> images;

    public GiphyImage() {
        // Empty constructor required because otherwise the Parcelable constructor
        // prevents GSON from being able to instantiate GiphyImages via reflection.
    }

    private ImageVariation getPreferredVariation() {
        return images.get("fixed_width");
    }

    public String getUrl() {
        return getPreferredVariation().webp;
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

    /// PARCELABLE IMPLEMENTATION

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(getUrl());
        out.writeInt(getWidth());
        out.writeInt(getHeight());
    }

    private GiphyImage(Parcel in) {
        ImageVariation variation = new ImageVariation();
        variation.webp = in.readString();
        variation.width = in.readInt();
        variation.height = in.readInt();
        images = new HashMap<>();
        images.put("fixed_width", variation);
    }

    public static final Creator<GiphyImage> CREATOR = new Creator<GiphyImage>() {
        @Override
        public GiphyImage createFromParcel(Parcel source) {
            return new GiphyImage(source);
        }

        @Override
        public GiphyImage[] newArray(int size) {
            return new GiphyImage[size];
        }
    };
}

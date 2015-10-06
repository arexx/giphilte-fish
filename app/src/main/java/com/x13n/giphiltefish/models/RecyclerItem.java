package com.x13n.giphiltefish.models;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * The abstract base class representing a thing that can appear in the search fragment recycler
 * view. The view mostly contains animated gifs but may also contain a logo, search bar, section
 * dividers and loading indicators.
 *
 * Created by alex on 05/10/15.
 */
public abstract class RecyclerItem {

    @LayoutRes
    public abstract int getLayoutId();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(RecyclerItem item) {
            throw new RuntimeException("ViewHolder has no bind implementation");
        }
    }

}

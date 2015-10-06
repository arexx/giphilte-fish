package com.x13n.giphiltefish.models;

import com.x13n.giphiltefish.net.giphy.model.GiphyImage;

/**
 * Generous interface for events that might occur as a result of actions within RecyclerItems.
 *
 * The fragment owning the app's main recycler view implements this interface so that it knows
 * when items are touched and search terms are entered.
 *
 * Created by alex on 06/10/15.
 */
public interface RecyclerItemListener {
    void onSearchTermEntered(String searchTerm);
    void onImageTouched(GiphyImage image);
}

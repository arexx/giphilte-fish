package com.x13n.giphiltefish.models;

import android.view.View;

import com.x13n.giphiltefish.R;
import com.x13n.giphiltefish.net.giphy.model.GiphyImage;
import com.x13n.giphiltefish.views.GiphyDraweeView;

/**
 * The data for an item that may appear in the search recycler view representing a giphy image.
 * Created by alex on 05/10/15.
 */
public class GiphyImageItem extends RecyclerItem {
    private GiphyImage mImage;

    public GiphyImageItem(GiphyImage image) {
        mImage = image;
    }

    public static class ViewHolder extends RecyclerItem.ViewHolder {
        private GiphyDraweeView mDraweeView;
        private RecyclerItemListener mRecyclerItemListener;

        public ViewHolder(View v) {
            super(v);
            mDraweeView = (GiphyDraweeView) v.findViewById(R.id.gif);
            mDraweeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerItemListener.onImageTouched(mDraweeView.getGiphyImage());
                }
            });
        }

        @Override
        public void bind(RecyclerItem item, RecyclerItemListener recyclerItemListener) {
            mRecyclerItemListener = recyclerItemListener;
            final GiphyImageItem imageItem = (GiphyImageItem)item;
            mDraweeView.setGiphyImage(imageItem.getImage());

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_giphy_image;
    }

    public GiphyImage getImage() {
        return mImage;
    }
}

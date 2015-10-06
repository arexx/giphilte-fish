package com.x13n.giphiltefish;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.x13n.giphiltefish.net.giphy.model.GiphyImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides views for the search fragment RecyclerView.
 *
 * Created by alex on 05/10/15.
 */
public class SearchAdapter extends android.support.v7.widget.RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final List<GiphyImage> mModel;

    /**
     * Given a list of new images fetched from the server, append them to the list.
     */
    public void addImages(List<GiphyImage> images) {
        mModel.addAll(images);
        notifyItemRangeInserted(mModel.size() - images.size(), images.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public SimpleDraweeView mDraweeView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text);
            mDraweeView = (SimpleDraweeView) v.findViewById(R.id.gif);
        }
    }

    public SearchAdapter() {
        mModel = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imageUrl = mModel.get(position).getUrl();
        holder.mTextView.setText("URL: " + imageUrl);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(imageUrl))
                .setAutoPlayAnimations(true)
                .build();

        holder.mDraweeView.setController(controller);
    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }



}

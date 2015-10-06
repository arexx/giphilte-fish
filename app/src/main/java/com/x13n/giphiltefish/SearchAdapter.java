package com.x13n.giphiltefish;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Provides views for the search fragment RecyclerView.
 *
 * Created by alex on 05/10/15.
 */
public class SearchAdapter extends android.support.v7.widget.RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final String[] mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text);
        }
    }

    public SearchAdapter() {
        mDataset = new String[]{
            "http://media2.giphy.com/media/AT2Q1Qve3Unde/200w.gif",
            "http://media0.giphy.com/media/3o85xJvFMYJzsOZOWQ/200w.gif",
            "http://media0.giphy.com/media/13r6ty71MGoBfG/200w.gif",
            "http://media0.giphy.com/media/GjonBzjN5b0Zy/200w.gif",
            "http://media0.giphy.com/media/M6dF0cbS41Pck/200w.gif",
            "http://media4.giphy.com/media/4eCb65QoBQfaE/200w.gif",
            "http://media3.giphy.com/media/qabALTpoZZmoM/200w.gif",
            "http://media3.giphy.com/media/ZIpgsdc67pYzK/200w.gif",
            "http://media2.giphy.com/media/143OOSsuEtK5NK/200w.gif",
            "http://media2.giphy.com/media/J1x49f9CiAmI0/200w.gif",
            "http://media0.giphy.com/media/gVAT5G1IoF3Jm/200w.gif",
            "http://media0.giphy.com/media/2niGkPmJL3NFm/200w.gif",
            "http://media3.giphy.com/media/Ph2EUpM4KAmAM/200w.gif",
            "http://media2.giphy.com/media/Rex1YbGuXCvYY/200w.gif",
            "http://media4.giphy.com/media/AisrIocRKt8iI/200w.gif",
            "http://media1.giphy.com/media/rZhB85CCeHdgA/200w.gif",
            "http://media2.giphy.com/media/lH222o7HcuCC4/200w.gif",
            "http://media1.giphy.com/media/kEbw8vXGwCQqk/200w.gif",
            "http://media4.giphy.com/media/n2paxU5JLX8Sk/200w.gif",
            "http://media0.giphy.com/media/Qh6NZWsFx1G1O/200w.gif",
            "http://media2.giphy.com/media/no2JeUjkz6EBG/200w.gif",
            "http://media4.giphy.com/media/tolFEWW90XwoE/200w.gif",
            "http://media2.giphy.com/media/13Jyycjoo101uE/200w.gif",
            "http://media0.giphy.com/media/upztYklL3VhNm/200w.gif",
            "http://media4.giphy.com/media/a2SR6Ag8ChUlO/200w.gif"
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}

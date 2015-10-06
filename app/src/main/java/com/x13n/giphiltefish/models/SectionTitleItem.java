package com.x13n.giphiltefish.models;

import android.view.View;
import android.widget.TextView;

import com.x13n.giphiltefish.R;

/**
 * A data object representing a section title item in the recycler view.
 * Created by alex on 06/10/15.
 */
public class SectionTitleItem extends RecyclerItem {
    private String mTitle;

    public SectionTitleItem(String title) {
        mTitle = title;
    }

    public static class ViewHolder extends RecyclerItem.ViewHolder {
        private TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.title);
        }

        @Override
        public void bind(RecyclerItem item) {
            SectionTitleItem titleItem = (SectionTitleItem)item;
            mTextView.setText(titleItem.getTitle());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_section_title;
    }

    public String getTitle() {
        return mTitle;
    }
}

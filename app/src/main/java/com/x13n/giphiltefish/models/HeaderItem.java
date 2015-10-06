package com.x13n.giphiltefish.models;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.x13n.giphiltefish.R;

/**
 * Logo and search bar
 *
 * Created by alex on 06/10/15.
 */
public class HeaderItem extends RecyclerItem {

    public static class ViewHolder extends RecyclerItem.ViewHolder {
        private RecyclerItemListener mRecyclerItemListener;
        private EditText mEditText;

        public ViewHolder(View v) {
            super(v);
            mEditText = (EditText) v.findViewById(R.id.search);
            mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        mRecyclerItemListener.onSearchTermEntered(v.getText().toString());

                        // Magic incantation to hide soft keyboard on search
                        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        return true;
                    }

                    return false;
                }
            });
        }

        @Override
        public void bind(RecyclerItem item, RecyclerItemListener recyclerItemListener) {
            mRecyclerItemListener = recyclerItemListener;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_header;
    }
}

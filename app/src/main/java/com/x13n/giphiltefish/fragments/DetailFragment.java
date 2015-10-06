package com.x13n.giphiltefish.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.x13n.giphiltefish.R;
import com.x13n.giphiltefish.net.giphy.model.GiphyImage;
import com.x13n.giphiltefish.views.GiphyDraweeView;

/**
 * Shows a single Giphy image and options to share
 *
 * Created by alex on 06/10/15.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {
    public static final String IMAGE_KEY = "image";
    private GiphyImage mImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detail, container, false);

        GiphyDraweeView draweeView = (GiphyDraweeView) layout.findViewById(R.id.gif);
        mImage = getArguments().getParcelable(IMAGE_KEY);
        draweeView.setGiphyImage(mImage);

        layout.findViewById(R.id.button_sms).setOnClickListener(this);
        layout.findViewById(R.id.button_clipboard).setOnClickListener(this);


        return layout;
    }

    @Override
    public void onClick(View v) {

    }
}

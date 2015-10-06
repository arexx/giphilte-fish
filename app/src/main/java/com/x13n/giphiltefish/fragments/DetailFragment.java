package com.x13n.giphiltefish.fragments;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.x13n.giphiltefish.R;
import com.x13n.giphiltefish.helpers.StubHelper;
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
        if (v.getId() == R.id.button_sms) {
            sendSms();
        } else if (v.getId() == R.id.button_clipboard) {
            copyToClipboard();
        }
    }

    private void sendSms() {
        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("sms_body", "Check out this super sick GIF: " + mImage.getShareUrl());
        try {
            startActivity(smsIntent);
        } catch (ActivityNotFoundException unused) {
            StubHelper.showYouBrokeItDialog("Your device doesn't have an SMS app so I can't send an SMS. Perhaps it is a tablet?", getActivity());
        }
    }

    private void copyToClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", mImage.getShareUrl());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(getActivity(), "URL copied to clipboard", Toast.LENGTH_SHORT).show();
    }
}

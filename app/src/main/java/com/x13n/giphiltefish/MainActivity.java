package com.x13n.giphiltefish;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.x13n.giphiltefish.fragments.DetailFragment;
import com.x13n.giphiltefish.fragments.SearchFragment;
import com.x13n.giphiltefish.net.giphy.model.GiphyImage;

public class MainActivity extends Activity implements SearchFragment.OnImageSelectedListener {
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new SearchFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onImageSelected(GiphyImage image) {
        Fragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(DetailFragment.IMAGE_KEY, image);
        fragment.setArguments(args);

        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.fade_in, R.animator.fade_out,
                        R.animator.fade_in, R.animator.fade_out)
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}

package com.x13n.giphiltefish.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.x13n.giphiltefish.net.giphy.model.GiphyImage;

/**
 * An extension of Fresco's SimpleDraweeView that configures itself given a GiphyImage, and that
 * also calculates its height based on its width (for nifty fixed-width variable-height list
 * display)
 *
 * Created by alex on 05/10/15.
 */
public class GiphyDraweeView extends SimpleDraweeView {
    private GiphyImage mImage;

    public GiphyDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public GiphyDraweeView(Context context) {
        super(context);
    }

    public GiphyDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GiphyDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GiphyImage getGiphyImage() {
        return mImage;
    }

    public void setGiphyImage(GiphyImage image) {
        mImage = image;
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(image.getUrl()))
                .setAutoPlayAnimations(true)
                .build();
        setController(controller);

        // Given a new image, the aspect ratio of the view has probably changed so the layout should
        // recomputed.
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*
         The goal is for the view to be sized to suit the aspect ratio of the image when the
         width is constrained but the height is free.

         This StackOverflow post gave me a lot of help understanding the method contract for this
         solution, as well as a template:
         http://stackoverflow.com/questions/12266899/onmeasure-custom-view-explanation
         */

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        int desiredWidth = (mImage == null) ? 100 : mImage.getWidth();

        // Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            // Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            // Agree to use all available width
            width = widthSize;
        } else {
            // Lacking guidance, just be the native width
            width = desiredWidth;
        }

        // Aim to be tall enough to match the aspect ratio of the source image
        int desiredHeight = (mImage == null) ? 100 : mImage.getHeight(width);

        // Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            // Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            // Expand until we reach our desired size
            height = Math.min(desiredHeight, heightSize);
        } else {
            // We're free to be whatever height suits the width
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }
}

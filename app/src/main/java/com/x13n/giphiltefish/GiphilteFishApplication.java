package com.x13n.giphiltefish;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * The application class.
 *
 * While Google discourage subclassing the application class and recommend instead creating
 * singletons, Facebook require that Fresco is initialized before any activities are created.
 * See:
 *
 * http://frescolib.org/docs/getting-started.html
 *
 * Created by alex on 05/10/15.
 */
public class GiphilteFishApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}

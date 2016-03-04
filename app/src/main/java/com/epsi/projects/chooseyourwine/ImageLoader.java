package com.epsi.projects.chooseyourwine;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by boriest on 04/03/2016.
 */
public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
    private ImageView view;

    public ImageLoader(ImageView view) {
        this.view = view;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            InputStream is = url.openConnection().getInputStream();
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        this.view.setImageBitmap(bitmap);
    }

}

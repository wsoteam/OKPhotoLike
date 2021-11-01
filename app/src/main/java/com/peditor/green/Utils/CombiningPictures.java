package com.peditor.green.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class CombiningPictures {

    public static Bitmap overlay(ImageView view1, ImageView view2) {
        BitmapDrawable getFirstImage = (BitmapDrawable) view1.getDrawable();
        BitmapDrawable getSecondImage = (BitmapDrawable) view2.getDrawable();
        Bitmap firstImage = getFirstImage.getBitmap();

        Bitmap secondImage = getSecondImage.getBitmap();
        secondImage = Bitmap.createScaledBitmap(secondImage, firstImage.getWidth(), firstImage.getHeight(), false);
        Bitmap bmOverlay = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(firstImage, new Matrix(), null);
        canvas.drawBitmap(secondImage, 0, 0, null);
        return bmOverlay;
    }
}

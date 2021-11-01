package com.peditor.green.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssetsHelper {
    public List<String> getImageNames(Context context, String path) throws IOException {
        AssetManager assetManager = context.getAssets();
        String[] files = assetManager.list(path);

        List<String> array = new ArrayList<>();
        array.addAll(Arrays.asList(files));
        return array;
    }

    public List<Bitmap> getImagesFromAsset(Context context, List<String> array_short_path, String full_path) {
        List<Bitmap> map = new ArrayList<>();
        try {

            for (int i = 0; i < array_short_path.size(); i++) {
                InputStream bitmap = context.getAssets().open(full_path + array_short_path.get(i));
                Bitmap bit = BitmapFactory.decodeStream(bitmap);
                //bit = Bitmap.createScaledBitmap(bit, Variables.WIDTH, Variables.HEIGHT, false);
                map.add(bit);

            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return map;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        Bitmap bitmap = Bitmap.createScaledBitmap(image, width, height, true);
        return bitmap;
    }

    public Bitmap getStockImageSize(ImageView image) {
        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap a = drawable.getBitmap();
        Variables.WIDTH = a.getWidth();
        Variables.HEIGHT = a.getHeight();
        return a;
    }

}

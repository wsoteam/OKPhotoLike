package com.peditor.green.Fragments;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.peditor.green.R;
import com.peditor.green.activity.EditActivity;

public class ReflectionFragment extends Fragment {
    private int FLIP_VERTICAL = 1;
    private int FLIP_HORIZONTAL = -1;
    private Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewReflection = inflater.inflate(R.layout.fragment_reflection, container, false);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) EditActivity.image.getDrawable();
        bitmap = bitmapDrawable.getBitmap();

        viewReflection.findViewById(R.id.btnHorizontal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = flipImage(bitmap, FLIP_HORIZONTAL);
                EditActivity.image.setImageBitmap(bitmap);
            }
        });

        viewReflection.findViewById(R.id.btnVertical).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = flipImage(bitmap, FLIP_VERTICAL);
                EditActivity.image.setImageBitmap(bitmap);
            }
        });
        return viewReflection;
    }

    protected Bitmap flipImage(Bitmap src, int type) {
        Matrix matrix = new Matrix();
        if (type == FLIP_VERTICAL) {
            matrix.preScale(1.0f, -1.0f);
        } else if (type == FLIP_HORIZONTAL) {
            matrix.preScale(-1.0f, 1.0f);

        } else {
            return null;
        }
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }
}
package com.peditor.green.Fragments;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.peditor.green.R;
import com.peditor.green.activity.EditActivity;

public class BrightnessGreenFragment extends Fragment {

    private Bitmap bitmap;
    private SeekBar seekBar;
    private SeekBar seekBar2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoundCorner = inflater.inflate(R.layout.fragment_brightness_green, container, false);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) EditActivity.image.getDrawable();
        bitmap = bitmapDrawable.getBitmap();
        seekBar = viewRoundCorner.findViewById(R.id.seekBarCorner_green);
        seekBar.setProgress(125);

        seekBar2 = viewRoundCorner.findViewById(R.id.seekBarCorner_green2);
        seekBar2.setProgress(125);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                EditActivity.image.setColorFilter(setBrightness(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                EditActivity.image.setColorFilter(setBrightness2(progress2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {

            }
        });
        return viewRoundCorner;
    }


    public static PorterDuffColorFilter setBrightness(int progress) {
        if (progress >=    100)
        {
            int value = (int) (progress-100) * 200 / 100;

            return new PorterDuffColorFilter(Color.argb(value, 39, 148, 3), PorterDuff.Mode.SRC_OVER);

        }
        else
        {
            int value = (int) (100-progress) * 200 / 100;
            return new PorterDuffColorFilter(Color.argb(value, 39, 148, 3), PorterDuff.Mode.SRC_ATOP);


        }
    }
    public static PorterDuffColorFilter setBrightness2(int progress2) {
        if (progress2 >=    100)
        {
            int value = (int) (progress2-100) * 200 / 100;

            return new PorterDuffColorFilter(Color.argb(value, 221, 224, 0), PorterDuff.Mode.SRC_OVER);

        }
        else
        {
            int value = (int) (100-progress2) * 200 / 100;
            return new PorterDuffColorFilter(Color.argb(value, 221, 224, 0), PorterDuff.Mode.SRC_ATOP);


        }
    }
}

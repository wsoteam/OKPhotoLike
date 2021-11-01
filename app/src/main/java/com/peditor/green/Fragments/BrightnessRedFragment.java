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


public class BrightnessRedFragment extends Fragment {

    private Bitmap bitmap;
    private SeekBar seekBar;
    private SeekBar seekBar2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoundCorner = inflater.inflate(R.layout.fragment_brightness_red, container, false);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) EditActivity.image.getDrawable();
        bitmap = bitmapDrawable.getBitmap();
        seekBar = viewRoundCorner.findViewById(R.id.seekBarCorner_red);
        seekBar.setProgress(125);

        seekBar2 = viewRoundCorner.findViewById(R.id.seekBarCorner_red2);
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

            return new PorterDuffColorFilter(Color.argb(value, 247, 12, 12), PorterDuff.Mode.SRC_OVER);

        }
        else
        {
            int value = (int) (100-progress) * 200 / 100;
            return new PorterDuffColorFilter(Color.argb(value, 247, 12, 12), PorterDuff.Mode.SRC_ATOP);
        }
    }
    public static PorterDuffColorFilter setBrightness2(int progress2) {
        if (progress2 >=    100)
        {
            int value = (int) (progress2-100) * 200 / 100;

            return new PorterDuffColorFilter(Color.argb(value, 224, 108, 0), PorterDuff.Mode.SRC_OVER);

        }
        else
        {
            int value = (int) (100-progress2) * 200 / 100;
            return new PorterDuffColorFilter(Color.argb(value, 224, 108, 0), PorterDuff.Mode.SRC_ATOP);
        }
    }


   /*private Bitmap bitmap1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stickers, container, false);

        List<Bitmap> arrayListFrames;
        List<String> arrayListFramesNames = null;


        AssetsHelper assetsHelper = new AssetsHelper();
        try {
            arrayListFramesNames = assetsHelper.getImageNames(Variables.context, "stickers");
        } catch (IOException e) {
            e.printStackTrace();
        }
        arrayListFrames = assetsHelper.getImagesFromAsset(Variables.context, arrayListFramesNames, "stickers" + "/");

        ConstraintLayout parentContainer = (ConstraintLayout) view.findViewById(R.id.stickersLayout);

        HorizontalScrollView scrollView = new HorizontalScrollView(Variables.context);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        parentContainer.addView(scrollView);


        LinearLayout linearLayout = new LinearLayout(Variables.context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutForOuter = new LinearLayout.LayoutParams(200, 200);
        linearLayout.setLayoutParams(layoutForOuter);

        scrollView.addView(linearLayout);

        for (int i = 0; i < arrayListFrames.size(); i++) {
            ImageView imageView = new ImageView(Variables.context);
            Bitmap bitmap = arrayListFrames.get(i);

            imageView.setId(i);
            imageView.setAdjustViewBounds(true);

            imageView.setImageBitmap(bitmap);
/////////////////////////////////////////////

            BitmapDrawable drawable = (BitmapDrawable) EditActivity.image.getDrawable();
            Bitmap a = drawable.getBitmap();
            Drawable drawable1 = new BitmapDrawable(getResources(), a);
            imageView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//////////////////////////////////////////////
            linearLayout.addView(imageView);



            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bitmap1 = Bitmap.createScaledBitmap(bitmap, Variables.WIDTH, Variables.HEIGHT, false);
                    EditActivity.secondImage.setImageBitmap(bitmap1);


                    imageView.setAlpha(0f);
                    imageView.setTranslationY(50);

                    imageView.animate().alpha(1f).translationYBy(-50).setDuration(1500);

                }
            });
        }
        return view;
    }*/

}

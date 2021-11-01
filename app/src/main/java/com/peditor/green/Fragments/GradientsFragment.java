package com.peditor.green.Fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.peditor.green.R;
import com.peditor.green.Utils.AssetsHelper;
import com.peditor.green.Utils.Variables;
import com.peditor.green.activity.EditActivity;

import java.io.IOException;
import java.util.List;

public class GradientsFragment extends Fragment {
    private Bitmap gradient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gradients, container, false);

        List<Bitmap> arrayListGradients;
        List<String> arrayListGradientsNames = null;

        AssetsHelper assetsHelper = new AssetsHelper();
        try {
            arrayListGradientsNames = assetsHelper.getImageNames(Variables.context, "gradients");
        } catch (IOException e) {
            e.printStackTrace();
        }
        arrayListGradients = assetsHelper.getImagesFromAsset(Variables.context, arrayListGradientsNames, "gradients" + "/");

        ConstraintLayout parentContainer = (ConstraintLayout)v.findViewById(R.id.gradientsContainer);

        HorizontalScrollView scrollView = new HorizontalScrollView(Variables.context);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        parentContainer.addView(scrollView);


        LinearLayout linearLayout = new LinearLayout(Variables.context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutForOuter = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutForOuter);

        scrollView.addView(linearLayout);

        for (int i = 0; i < arrayListGradients.size(); i++) {
            ImageView imageView = new ImageView(Variables.context);
            Bitmap bitmap = arrayListGradients.get(i);

            imageView.setId(i);
            imageView.setAdjustViewBounds(true);

            imageView.setImageBitmap(bitmap);

            BitmapDrawable drawable = (BitmapDrawable) EditActivity.image.getDrawable();
            Bitmap a = drawable.getBitmap();
            Drawable drawable1 = new BitmapDrawable(getResources(), a);
            imageView.setBackgroundDrawable(drawable1);

            linearLayout.addView(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gradient = Bitmap.createScaledBitmap(bitmap, Variables.WIDTH, Variables.HEIGHT, false);
                    EditActivity.secondImage.setImageBitmap(gradient);
                }
            });
        }

        return v;
    }
}
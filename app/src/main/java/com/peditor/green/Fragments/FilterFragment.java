package com.peditor.green.Fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.peditor.green.R;
import com.peditor.green.Utils.Variables;
import com.peditor.green.activity.EditActivity;
import com.peditor.green.photoFilters.PhotoFilters;

public class FilterFragment extends Fragment{
    private Bitmap bmp;
    private View view;
    private PhotoFilters photoFilters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_filter, container, false);
        photoFilters = new PhotoFilters();
        BitmapDrawable abmp = (BitmapDrawable) EditActivity.image.getDrawable();
        bmp = abmp.getBitmap();
        clickMethod();
        return view;
    }

    private void clickMethod() {
        view.findViewById(R.id.btnEffect1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.one(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.two(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.three(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.four(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.five(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.six(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.seven(Variables.context, bmp));
            }
        });


        view.findViewById(R.id.btnEffect8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.eight(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.nine(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.ten(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.eleven(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.twelve(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.thirteen(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.fourteen(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.fifteen(Variables.context, bmp));
            }
        });

        view.findViewById(R.id.btnEffect16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.image.setImageBitmap(photoFilters.sixteen(Variables.context, bmp));
            }
        });
    }
}
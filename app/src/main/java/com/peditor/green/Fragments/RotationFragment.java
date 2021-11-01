package com.peditor.green.Fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.fragment.app.Fragment;

import com.peditor.green.R;
import com.peditor.green.activity.EditActivity;

public class RotationFragment extends Fragment {
    private int h, w;
    private Boolean safe = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRotate = inflater.inflate(R.layout.fragment_rotation, container, false);

        viewRotate.findViewById(R.id.btnRotate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditActivity.image.getRotation() / 90 % 2 == 0) {
                    h = EditActivity.image.getHeight();
                    w = EditActivity.image.getWidth();

                }
                if (safe)
                    EditActivity.image.animate().rotationBy(90).scaleX(EditActivity.image.getRotation() / 90 % 2 == 0 ? (w * 1.0f / h) : 1).scaleY(EditActivity.image.getRotation() / 90 % 2 == 0 ? (w * 1.0f / h) : 1).setDuration(2000).setInterpolator(new LinearInterpolator()).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            safe = false;
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            safe = true;

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).start();
            }
        });
        return viewRotate;
    }
}
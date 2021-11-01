package com.peditor.green.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.peditor.green.Fragments.BrightnessBlueFragment;
import com.peditor.green.Fragments.BrightnessGreenFragment;
import com.peditor.green.Fragments.BrightnessRedFragment;
import com.peditor.green.Utils.AssetsHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.peditor.green.Adapter.ViewAdapter;
import com.peditor.green.Fragments.EffectFragment;
import com.peditor.green.Fragments.FilterFragment;
import com.peditor.green.Fragments.FramesFragment;
import com.peditor.green.Fragments.GradientsFragment;
import com.peditor.green.Fragments.MirrorFragment;
import com.peditor.green.Fragments.ReflectionFragment;
import com.peditor.green.Fragments.RotationFragment;
import com.peditor.green.Fragments.BrightnessFragment;
import com.peditor.green.R;
import com.peditor.green.Utils.CombiningPictures;
import com.peditor.green.Utils.Variables;

import java.io.IOException;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private final int IMAGE_REQUEST_CODE = 13;
    private  ViewAdapter adapter;
    @SuppressLint("StaticFieldLeak")
    public static ImageView secondImage, image;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Bitmap resetImage;
    private AssetsHelper assetsHelper;

    Uri saveImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
        image.setImageResource(R.drawable.kek);
        assetsHelper.getStockImageSize(image);
        generateFragments();
    }

    private void init() {
        assetsHelper = new AssetsHelper();
        Variables.context = getApplicationContext();
        image = findViewById(R.id.image);
        secondImage = findViewById(R.id.secondImage);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab_layout);
        secondImage.setImageResource(R.drawable.transparent_image);
    }

    private void generateFragments() {
        adapter = new ViewAdapter(getSupportFragmentManager());
        adapter.clear();
        adapter.addFragment(new GradientsFragment(), "");
        adapter.addFragment(new BrightnessFragment(), "");
        adapter.addFragment(new BrightnessRedFragment(), "");
        adapter.addFragment(new BrightnessGreenFragment(), "");
        adapter.addFragment(new BrightnessBlueFragment(), "");
        adapter.addFragment(new EffectFragment(), "");
        adapter.addFragment(new FramesFragment(), "");
        adapter.addFragment(new FilterFragment(), "");
        adapter.addFragment(new RotationFragment(), "");
        adapter.addFragment(new ReflectionFragment(), "");
        adapter.addFragment(new MirrorFragment(), "");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        try {
            setTabIcon();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveImageToGallery(ImageView imageView){
        imageView.setDrawingCacheEnabled(true);
        Bitmap b = imageView.getDrawingCache();
        MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), b,
                getString(R.string.title) + image.getId(), "Image");
        //saveImageUri = uri;

        Toast.makeText(this, "Please wait while the photo is being saved", Toast.LENGTH_SHORT).show();
    }
    private void shareImage() {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);

        Uri screenshotUri = Uri.parse(MediaStore.Images.Media.EXTERNAL_CONTENT_URI + "/" + image.getId());
        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        shareIntent.setType("image/*jpeg/*");
        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.msg_share_image)));

    }
    protected void showSnackbar(@NonNull String message) {
        View view = findViewById(android.R.id.content);
        if (view != null) {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void setTabIcon() throws IOException {
        List<String> names = assetsHelper.getImageNames(this, "icons");
        List<Bitmap> array = assetsHelper.getImagesFromAsset(this, names, "icons" + "/");
        for(int i = 0; i < array.size(); i++) {
            Drawable drawable = new BitmapDrawable(getResources(), array.get(i));
            tabLayout.getTabAt(i).setIcon(drawable);
        } ///
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_open) {
            if(ActivityCompat.checkSelfPermission(EditActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EditActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 32);
            } else {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }
            return true;
        }

        if (id == R.id.action_apply) {
            Bitmap bot = CombiningPictures.overlay(image, secondImage);
            image.setImageDrawable(null);
            secondImage.setImageResource(R.drawable.transparent_image);
            image.setImageBitmap(bot);
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id == R.id.action_clear) {
            secondImage.setImageResource(R.drawable.transparent_image);
            Toast.makeText(this, "Clear", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_reset) {
            if(resetImage != null) {
                image.setImageBitmap(resetImage);
                secondImage.setImageResource(R.drawable.transparent_image);
            } else {
                Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        if (id == R.id.action_save) {
            saveImageToGallery(image);
            return true;
        }
        if (id == R.id.action_share) {
            shareImage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST_CODE) {

            if(data.getData() != null) {
                Uri filePath = data.getData();
                Variables.URI_IMAGE = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
                    if (bitmap.getWidth() > 2000) {
                        bitmap = assetsHelper.getResizedBitmap(bitmap, 500);

                        Variables.HEIGHT = bitmap.getWidth();
                        Variables.WIDTH = bitmap.getHeight();

                    } else {
                        Variables.WIDTH = bitmap.getWidth();
                        Variables.HEIGHT = bitmap.getHeight();
                    }
                    image.setImageBitmap(bitmap);
                    BitmapDrawable getResetImage = (BitmapDrawable) image.getDrawable();
                    resetImage = getResetImage.getBitmap();
                    generateFragments();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
    }
}
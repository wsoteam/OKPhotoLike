package com.peditor.green.photoFilters;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.Matrix4f;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicColorMatrix;
import android.renderscript.ScriptIntrinsicConvolve3x3;

import androidx.annotation.RequiresApi;

public class PhotoFilters {
    RenderScript renderScript;
    Allocation inputAllocation;
    Allocation outputAllocation;
    Bitmap outBitmap;

    public PhotoFilters() {
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap one(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix1 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix1.setColorMatrix(new Matrix4f(new float[]{-0.33F, -0.33F, -0.33F, 1.0F, -0.59F, -0.59F, -0.59F, 1.0F, -0.11F, -0.11F, -0.11F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F}));
        colorMatrix1.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap two(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix2 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix2.setGreyscale();
        colorMatrix2.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap three(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix3 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix3.setColorMatrix(new Matrix4f(new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.78F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F}));
        colorMatrix3.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap four(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix4 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix4.setColorMatrix(new Matrix4f(new float[]{0.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.65F, 0.0F, 0.0F, 0.0F, 0.0F, 0.49F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F}));
        colorMatrix4.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap five(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix5 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix5.setColorMatrix(new Matrix4f(new float[]{-0.3597053F, 0.37725273F, 0.66384166F, 0.0F, 1.5668082F, 0.4566682F, 1.1261392F, 0.0F, -0.14710288F, 0.22607906F, -0.7299808F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F}));
        colorMatrix5.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap six(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix6 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix6.setColorMatrix(new Matrix4f(new float[]{1.2F, 0.1F, 0.2F, 0.7F, 0.7F, 1.0F, 0.0F, -0.5F, -0.7F, 0.2F, 0.5F, 1.3F, 0.0F, -0.1F, 0.0F, 0.9F}));
        colorMatrix6.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap seven(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix7 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix7.setColorMatrix(new Matrix4f(new float[]{1.229946F, 0.020952377F, 0.38324407F, 0.0F, 0.4501389F, 1.1873742F, -0.10693325F, -0.34008488F, 0.13167344F, 1.0636892F, 0.0F, 0.0F, 0.0F, 0.0F, 11.91F, 11.91F, 11.91F, 0.0F}));
        colorMatrix7.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap eight(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix8 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix8.setColorMatrix(new Matrix4f(new float[]{1.44F, 0.0F, 0.0F, 0.0F, 0.0F, 1.44F, 0.0F, 0.0F, 0.0F, 0.0F, 1.44F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F}));
        colorMatrix8.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap nine(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix9 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix9.setColorMatrix(new Matrix4f(new float[]{-2.0F, -1.0F, 1.0F, -2.0F, 0.0F, -2.0F, 0.0F, 1.0F, 0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F}));
        colorMatrix9.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap ten(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix10 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix10.setColorMatrix(new Matrix4f(new float[]{1.0F, 0.0F, 0.1F, -0.1F, 0.0F, 1.0F, 0.2F, 0.0F, 0.0F, 0.0F, 1.3F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F}));
        colorMatrix10.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap eleven(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix11 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix11.setColorMatrix(new Matrix4f(new float[]{1.728147F, -0.412105F, 0.541145F, 0.0F, 0.28937826F, 1.1883553F, -1.1763717F, 0.0F, -1.0175253F, 0.22374965F, 1.6352267F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F}));
        colorMatrix11.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap twelve(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix12 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix12.setColorMatrix(new Matrix4f(new float[]{0.309F, 0.409F, 0.309F, 0.0F, 0.609F, 0.309F, 0.409F, 0.0F, 0.42F, 0.42F, 0.2F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F}));
        colorMatrix12.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap thirteen(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicConvolve3x3 convolve1 = ScriptIntrinsicConvolve3x3.create(this.renderScript, Element.U8_4(this.renderScript));
        convolve1.setInput(this.inputAllocation);
        convolve1.setCoefficients(new float[]{-2.0F, -1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 2.0F});
        convolve1.forEach(this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap fourteen(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicConvolve3x3 convolve2 = ScriptIntrinsicConvolve3x3.create(this.renderScript, Element.U8_4(this.renderScript));
        convolve2.setInput(this.inputAllocation);
        convolve2.setCoefficients(new float[]{0.2F, 0.3F, 0.2F, 0.1F, 0.1F, 0.1F, 0.2F, 0.3F, 0.2F});
        convolve2.forEach(this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )
    public Bitmap fifteen(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix13 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix13.setColorMatrix(new Matrix4f(new float[]{2.1027913F, -0.29821262F, 0.42128146F, 0.0F, 0.22289757F, 1.687012F, -0.8834213F, 0.0F, -0.7656889F, 0.17120072F, 2.0221398F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F}));
        colorMatrix13.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }

    @RequiresApi(
            api = 17
    )

    public Bitmap sixteen(Context context, Bitmap bitmap) {
        this.renderScript = RenderScript.create(context);
        this.outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        this.inputAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        this.outputAllocation = Allocation.createTyped(this.renderScript, this.inputAllocation.getType());
        ScriptIntrinsicColorMatrix colorMatrix14 = ScriptIntrinsicColorMatrix.create(this.renderScript, Element.U8_4(this.renderScript));
        colorMatrix14.setColorMatrix(new Matrix4f(new float[]{1.2748853F, -0.22851132F, 0.44108868F, 0.0F, 0.32366425F, 0.9551408F, -0.7059358F, 0.0F, -0.6985495F, 0.17337048F, 1.164847F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F}));
        colorMatrix14.forEach(this.inputAllocation, this.outputAllocation);
        this.outputAllocation.copyTo(this.outBitmap);
        return this.outBitmap;
    }
}

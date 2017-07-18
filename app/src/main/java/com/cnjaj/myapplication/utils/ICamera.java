package com.cnjaj.myapplication.utils;

import android.graphics.Bitmap;
import android.hardware.Camera;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/1/16.
 */
public interface ICamera {
    void takePhoto(PhotoListener listener);

    void release();

    void preview(FrameLayout view, Camera.PreviewCallback cb);

    void stopPreview();

    interface PhotoListener {
        /**
         * 采集图片
         * Camera拍照之后用来得到的图片的url
         *
         * @param bitmap 照片的url
         */
        void fetchPhoto(byte[] bitmapByte, Bitmap bitmap);
    }
}

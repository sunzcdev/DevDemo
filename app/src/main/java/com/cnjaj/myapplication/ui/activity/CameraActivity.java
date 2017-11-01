package com.cnjaj.myapplication.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.cnjaj.myapplication.R;
import com.cnjaj.myapplication.utils.AndroidCamera;

import java.io.ByteArrayOutputStream;

public class CameraActivity extends AppCompatActivity implements Camera.PreviewCallback {

    public static final String[] PERMISSIONS = {Manifest.permission.CAMERA};
    private static final int REQUEST_CAMERA = 0x11;
    private AndroidCamera mCamera;
    private SurfaceView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraView = (SurfaceView) findViewById(R.id.camera_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.setVisibility(View.VISIBLE);
        switch (ActivityCompat.checkSelfPermission(this, PERMISSIONS[0])) {
            case PackageManager.PERMISSION_DENIED:
                ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CAMERA);
                break;
            case PackageManager.PERMISSION_GRANTED:
                preview();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    preview();
                }
                break;
        }
    }

    private void preview() {
        mCamera = AndroidCamera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        mCamera.preview(cameraView, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.setVisibility(View.INVISIBLE);
        if (mCamera != null)
            mCamera.release();
    }

    public void takePhoto(View view) {
        mCamera.getCamera().setOneShotPreviewCallback(this);
    }

    private void showDialog(Bitmap bitmap) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageBitmap(bitmap);
        dialog.setView(imageView);
        dialog.setTitle("拍照图片");
        dialog.show();
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        int width = parameters.getPreviewSize().width;
        int height = parameters.getPreviewSize().height;
        YuvImage yuv = new YuvImage(data, parameters.getPreviewFormat(), width, height, null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        yuv.compressToJpeg(new Rect(0, 0, width, height), 50, out);
        byte[] bytes = out.toByteArray();
        final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                showDialog(bitmap);
            }
        });
    }
}

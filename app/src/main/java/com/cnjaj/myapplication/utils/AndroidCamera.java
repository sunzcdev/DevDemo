package com.cnjaj.myapplication.utils;

import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.IOException;

/**
 * Created by sunzc on 2017/2/16.
 */
public class AndroidCamera implements ICamera {
    private static final String TAG = "AndroidCamera";
    private static AndroidCamera androidCamera;
    private Camera mCamera;
    private int cameraId;

    private AndroidCamera(int cameraId) throws IllegalStateException {
        int cameraNum = Camera.getNumberOfCameras();
        if (cameraNum < 1) {
            throw new IllegalStateException("没有摄像头");
        } else if (cameraNum == 1) {
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        } else {
            mCamera = Camera.open(cameraId);
        }
        this.cameraId = cameraId;
        Camera.Parameters params = mCamera.getParameters();
        MyLog.i(TAG, params.getSupportedPreviewFormats().toString());
        params.setPictureFormat(ImageFormat.JPEG);
        params.setPreviewFormat(ImageFormat.NV21);
        // 自动对焦
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.setParameters(params);
        setCameraPreviewOrientation(0);
        MyLog.i(TAG, "打开摄像机" + cameraId);
    }

    public static AndroidCamera open(int cameraId) throws IllegalStateException {
        if (androidCamera == null || androidCamera.isRelease())
            androidCamera = new AndroidCamera(cameraId);
        return androidCamera;
    }

    @Override
    public void takePhoto(final PhotoListener listener) {
        MyLog.i(TAG, "正在拍照");
        mCamera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                MyLog.i(TAG, "拍照成功" + bytes.length);
                listener.fetchPhoto(bytes, BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            }
        });
    }

    public void setCameraPreviewOrientation(int rotation) {
        Camera.CameraInfo info = getCameraInfo();
        int degrees = rotation * 90;
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        mCamera.setDisplayOrientation(result);
    }

    public Camera.CameraInfo getCameraInfo() {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        return info;
    }

    public Camera.Parameters getParameters() {
        return mCamera.getParameters();
    }

    public void setParameters(Camera.Parameters parameters) {
        mCamera.setParameters(parameters);
    }

    public void preview(SurfaceView surfaceView, final Camera.PreviewCallback cb) {
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                startPreview(holder, cb);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                MyLog.i(TAG, "surfaceChanged" + holder + "format:" + format + "width:" + width + "height" + height);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                release();
            }
        });
    }

    @Override
    public void preview(FrameLayout surfaceContainer, final Camera.PreviewCallback cb) {
        if (mCamera == null) {
            return;
        }
        surfaceContainer.removeAllViews();
        MyLog.i(TAG, "相机开始预览");
        SurfaceView surfaceView = new SurfaceView(surfaceContainer.getContext());
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        surfaceContainer.addView(surfaceView);
        preview(surfaceView, cb);
    }

    private void startPreview(SurfaceHolder holder, Camera.PreviewCallback cb) {
        if (mCamera == null) return;
        setPreviewDisplay(holder);
        if (cb != null)
            mCamera.setPreviewCallback(cb);
        mCamera.setDisplayOrientation(0);
        mCamera.startPreview();
        MyLog.i(TAG, "相机正在预览" + holder);
    }

    private void setPreviewDisplay(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
            MyLog.e(TAG, "摄像机预览失败: " + e.getMessage());
            mCamera.stopPreview();
            setPreviewDisplay(holder);
        }
    }

    @Override
    public void stopPreview() {
        try {
            mCamera.setPreviewDisplay(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        MyLog.i(TAG, "相机停止预览");
    }

    @Override
    public void release() {
        if (mCamera != null) {
            stopPreview();
            mCamera.release();
            mCamera = null;
            MyLog.i(TAG, "相机关闭");
        }
    }

    public Camera getCamera() {
        return mCamera;
    }

    public boolean isRelease() {
        return mCamera == null;
    }

    public int getCameraId() {
        return cameraId;
    }
}

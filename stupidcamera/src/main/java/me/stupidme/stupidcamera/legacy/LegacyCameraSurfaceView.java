package me.stupidme.stupidcamera.legacy;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by allen on 17-7-14.
 */

public class LegacyCameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback,
        Camera.AutoFocusCallback {

    private Camera mCamera;

    private LegacyCameraSurfaceView(Context context) {
        super(context);
    }

    private LegacyCameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LegacyCameraSurfaceView(Context context, Camera camera) {
        super(context);
        mCamera = camera;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onAutoFocus(boolean b, Camera camera) {

    }

}

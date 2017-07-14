package me.stupidme.stupidcamera;

/**
 * Created by allen on 17-7-14.
 */

public interface UniversalCameraCallback {

    void onCameraOpened();

    void onCameraStartPreview();

    void onCameraCaptured();

    void onCamearaStopPreview();

    void onCameraStoped();
}

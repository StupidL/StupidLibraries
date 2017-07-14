package me.stupidme.stupidcamera.legacy;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import me.stupidme.stupidcamera.R;
import me.stupidme.stupidcamera.UniversalCameraCallback;

public class LegacyCameraFragment extends Fragment {

    private static final String TAG = "LegacyCameraFragment";

    protected UniversalCameraCallback mUniversalCameraCallback;
    protected Camera mCamera;
    protected LegacyCameraSurfaceView mCameraSurfaceView;
    protected FocusView mFocusView;

    public LegacyCameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_legacy_camera, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
//        mCameraSurfaceView = view.findViewById(R.id.legacy_camera_surface_view);
//        mFocusView = view.findViewById(R.id.legacy_camera_focus_view);
        FrameLayout frameLayout = view.findViewById(R.id.camera_preview_container);
        frameLayout.addView(initCameraSurfaceView(view.getContext()));
        frameLayout.addView(initFocusView());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected LegacyCameraSurfaceView initCameraSurfaceView(Context context) {
        mCamera = getCameraInstance();
        if (mCamera == null) {
            throw new IllegalArgumentException("Can not get camera instance!");
        }
        Context ctx = getActivity();
        if (ctx == null)
            ctx = context;
        mCameraSurfaceView = new LegacyCameraSurfaceView(ctx, mCamera);
        return mCameraSurfaceView;
    }

    protected FocusView initFocusView() {

        return mFocusView;
    }

    protected Camera getCameraInstance() {
        return getCameraInstance(0);
    }

    protected Camera getCameraInstance(int id) {
        Camera camera = null;
        if (!hasCameraHardware()) {
            Log.d(TAG, "Device has no camera hardware.");
            return null;
        }
        try {
            camera = Camera.open(id);

            if (mUniversalCameraCallback != null) {
                mUniversalCameraCallback.onCameraOpened();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return camera;
    }

    protected boolean hasCameraHardware() {
        Activity activity = getActivity();
        if (activity == null)
            throw new IllegalStateException("No activity attached to this fragment");
        return activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

}

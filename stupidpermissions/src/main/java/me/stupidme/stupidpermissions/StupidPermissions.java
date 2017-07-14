package me.stupidme.stupidpermissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.List;

/**
 * Created by allen on 17-7-12.
 */

public class StupidPermissions {

    private int mRequestCode;

    public boolean hasPermissions(Context context, List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;
        if (context == null)
            throw new IllegalArgumentException("context is null, check permissions failed!");

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    public interface PermissionsCallback extends ActivityCompat.OnRequestPermissionsResultCallback {

        void onPermissionsGranted(int requestCode, List<String> permissions);

        void onPermissionsDenied(int requestCode, List<String> permissions);

    }
}

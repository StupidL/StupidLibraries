package me.stupidme.stupidpermissions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;

import java.util.Arrays;

/**
 * Created by allen on 17-7-12.
 */

public class RationaleDialog extends DialogFragment {

    private String mPositiveButtonString;
    private String mNegativeButtonString;
    private String mRetionaleContent;
    private int mRequestCode;
    private String[] mPermissions;

    private StupidPermissions.PermissionsCallback mPermissionCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        boolean isAtLeastJellyBeanMR1 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
        if (isAtLeastJellyBeanMR1
                && getParentFragment() != null
                && getParentFragment() instanceof StupidPermissions.PermissionsCallback) {
            mPermissionCallback = (StupidPermissions.PermissionsCallback) getParentFragment();
        } else if (context instanceof StupidPermissions.PermissionsCallback) {
            mPermissionCallback = (StupidPermissions.PermissionsCallback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPermissionCallback = null;
    }

    public AlertDialog show() {
        OnClickListener listener = new OnClickListener(this);
        return new AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .setPositiveButton(mPositiveButtonString, listener)
                .setNegativeButton(mNegativeButtonString, listener)
                .setMessage(mRetionaleContent)
                .create();
    }

    private class OnClickListener implements Dialog.OnClickListener {

        private Object mHost;

        OnClickListener(RationaleDialog dialog) {

        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == Dialog.BUTTON_NEGATIVE) {
                if (mPermissionCallback != null)
                    mPermissionCallback.onPermissionsDenied(mRequestCode, Arrays.asList(mPermissions));
                return;
            }
            ActivityCompat.requestPermissions(getActivity(), mPermissions, mRequestCode);
        }
    }

}

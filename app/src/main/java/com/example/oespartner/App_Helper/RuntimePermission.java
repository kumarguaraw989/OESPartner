package com.example.oespartner.App_Helper;

import android.Manifest;
import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.oespartner.R;
import com.google.android.material.snackbar.Snackbar;


public class RuntimePermission {
    private LinearLayout snackBarLayout;
    private Activity activity;

    public RuntimePermission(Activity activity, LinearLayout linearLayout) {
        this.activity = activity;
        this.snackBarLayout = linearLayout;
    }

    public void checkRuntimePermissionForStorage() {
        if (PermissionsUtils.checkSelfForStoragePermission(activity)) {
            requestStoragePermissions();
        }
    }

    public void checkRuntimePermissionForPhone() {
        if (PermissionsUtils.checkSelfForCallPermission(activity)) {
            requestCallPermission();
        }

    }

//        public void checkRuntimePermissionForLocation() {
//            if (PermissionsUtils.checkSelfPermissionForLocation(activity)) {
//                requestLocationPermissions();
//            } else {
//                ((HomeActivity) activity).processingLocation();
//            }
//        }

    public void requestStoragePermissions() {
        if (PermissionsUtils.shouldShowRequestForStoragePermission(activity)) {
            showSnackBar(R.string.storage_permission, PermissionsUtils.PERMISSIONS_STORAGE, PermissionsUtils.REQUEST_STORAGE);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSIONS_STORAGE, PermissionsUtils.REQUEST_STORAGE);
        }
    }

//        public void requestLocationPermissions() {
//            if (PermissionsUtils.shouldShowRequestForLocationPermission(activity)) {
//                showSnackBar(R.string.location_permission, PermissionsUtils.PERMISSIONS_LOCATION, PermissionsUtils.REQUEST_LOCATION);
//            } else {
//                PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSIONS_LOCATION, PermissionsUtils.REQUEST_LOCATION);
//            }
//        }


    public void requestCallPermission() {
        if (PermissionsUtils.shouldShowRequestForCallPermission(activity)) {
            showSnackBar(R.string.phone_call_permission, PermissionsUtils.PERMISSION_CALL, PermissionsUtils.REQUEST_CALL_PHONE);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_CALL, PermissionsUtils.REQUEST_CALL_PHONE);
        }
    }

    public void requestCameraPermission() {
        if (PermissionsUtils.shouldShowRequestForCameraPermission(activity)) {
            showSnackBar(R.string.phone_camera_permission, PermissionsUtils.PERMISSION_CAMERA, PermissionsUtils.REQUEST_CAMERA);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_CAMERA, PermissionsUtils.REQUEST_CAMERA);
        }
    }


    public void requestCameraPermissionForProfilePhoto() {
        if (PermissionsUtils.shouldShowRequestForCameraPermission(activity)) {
            showSnackBar(R.string.phone_camera_permission, PermissionsUtils.PERMISSION_CAMERA, PermissionsUtils.REQUEST_CAMERA_FOR_PROFILE_PHOTO);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_CAMERA, PermissionsUtils.REQUEST_CAMERA_FOR_PROFILE_PHOTO);
        }
    }

    public void requestStoragePermissionsForProfilePhoto() {
        if (PermissionsUtils.shouldShowRequestForStoragePermission(activity)) {
            showSnackBar(R.string.storage_permission, PermissionsUtils.PERMISSIONS_STORAGE, PermissionsUtils.REQUEST_STORAGE_FOR_PROFILE_PHOTO);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSIONS_STORAGE, PermissionsUtils.REQUEST_STORAGE_FOR_PROFILE_PHOTO);
        }
    }

    public void requestCameraAndRecordPermission() {
        if (PermissionsUtils.shouldShowRequestForContactPermission(activity)) {
            showSnackBar(!PermissionsUtils.checkPermissionForCameraAndMicrophone(activity) ? R.string.camera_audio_permission : !PermissionsUtils.isAudioRecordingPermissionGranted(activity) ? R.string.record_audio : !PermissionsUtils.isCameraPermissionGranted(activity) ? R.string.phone_camera_permission : R.string.camera_audio_permission, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, PermissionsUtils.REQUEST_CAMERA_AUDIO);
        } else {
            PermissionsUtils.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, PermissionsUtils.REQUEST_CAMERA_AUDIO);
        }
    }

    public void checkRuntimePermissionForCameraAndAudioRecording() {
        if (PermissionsUtils.checkSelfPermissionForLocation(activity)) {
            requestCameraAndRecordPermission();
        }
    }

//        public void checkRuntimePermissionForLocationActivity() {
//            if (PermissionsUtils.checkSelfPermissionForLocation(activity)) {
//                requestLocationPermissions();
//            } else {
//                ((MapActivity) activity).processingLocation();
//            }
//        }

    public void showSnackBar(int resId, final String[] permissions, final int requestCode) {
        Snackbar.make(snackBarLayout, resId,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok_alert, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PermissionsUtils.requestPermissions(activity, permissions, requestCode);
                    }
                }).show();
    }
}

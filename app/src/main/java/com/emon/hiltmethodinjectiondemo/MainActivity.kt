package com.emon.hiltmethodinjectiondemo

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val TAG = "Hilt-method-injection-demo"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var cameraManager: CameraManager
    private lateinit var permissionChecker: PermissionChecker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
    }

    @Inject
    fun initializeDependencies(permissionChecker: PermissionChecker) {
        this.permissionChecker = permissionChecker
        Log.d(TAG, "PermissionChecker initialized")
    }

    override fun onResume() {
        super.onResume()
        if (permissionChecker.hasCameraPermission()) {
            cameraManager.initializeCamera()
        } else {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 1001
    }
}

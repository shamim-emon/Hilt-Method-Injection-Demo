package com.emon.hiltmethodinjectiondemo

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import javax.inject.Inject
import javax.inject.Singleton
import android.Manifest
import dagger.hilt.android.qualifiers.ApplicationContext

@Singleton
class PermissionChecker @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
}

@Singleton
class CameraManager @Inject constructor() {
    fun initializeCamera() {
        Log.d(TAG, "Camera initialized")
    }
}

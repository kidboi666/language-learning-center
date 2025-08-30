package com.logmind.qrcodereader

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class QRCodeAnalyzer(val onDetectListener: OnDetectListener) : ImageAnalysis.Analyzer {

    private val scanner = BarcodeScanning.getClient()

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            /**
             * 이미지가 찍힐 당시 카메라의 회전 각도를 고려하여 입력 이미지를 생성한다.
             */
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            /**
             * [scanner.process]를 이용해 이미지를 분석한다.
             */
            scanner.process(image)
                .addOnSuccessListener { qrCodes ->
                    for (qrCode in qrCodes) {
                    onDetectListener.onDetect(qrCode.rawValue ?: "")
                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }
}
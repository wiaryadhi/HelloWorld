package com.bcafinance.training

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_portofolio.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class Portofolio : AppCompatActivity() {

    companion object {
        private val REQUEST_CODE_PERMISIONS = 999
        private val CAMERA_REQUEST = 998
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portofolio)

        imgCall.setOnClickListener() {
            val openCall = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:081338942027")
            }
            startActivity(openCall)
        }

        imgEmail.setOnClickListener() {
            val addresses: Array<String> =
                arrayOf("wiaryadhi@gmail.com", "gede.aryadi@juaracoding.com")
            val subject = "Android Studio"
            val openEmail = Intent(Intent.ACTION_SEND).apply {
                type = "*/*"
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }
            if (openEmail.resolveActivity(packageManager) != null) {
                startActivity(openEmail)
            }
        }

        imgMaps.setOnClickListener() {
            val mapsUrl = Uri.parse("geo:0,0?q=wisma+bca+pondok+indah")
            val openMaps = Intent(Intent.ACTION_VIEW, mapsUrl)
            openMaps.setPackage("com.google.android.apps.maps")
            startActivity(openMaps)
        }

        imgCamera.setOnClickListener() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    requestPermissions(permissions, REQUEST_CODE_PERMISIONS)
                } else {
                    captureCamera()
                }
            }
        }

    }

    fun captureCamera() {
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startActivityForResult(takeCamera, CAMERA_REQUEST)
    }

    fun saveImage(bitmap: Bitmap) {
        val eksStorage =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
        val tanggal = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fileName = eksStorage + "/BCAF_" + tanggal + ".png"

        var file: File? = null
        file = File(fileName)
        file.createNewFile()

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
        val bitmapData = bos.toByteArray()

        val fos = FileOutputStream(file)
        fos.write(bitmapData)
        fos.flush()
        fos.close()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISIONS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    captureCamera()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            val bitmapImage = data?.extras?.get("data") as Bitmap
            saveImage(bitmapImage)
            imgUser.setImageBitmap(bitmapImage)
        }
    }

}

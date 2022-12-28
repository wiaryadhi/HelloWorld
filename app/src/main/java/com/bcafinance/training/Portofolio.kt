package com.bcafinance.training

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_portofolio.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class Portofolio : AppCompatActivity() {

    companion object {
        //kodenya bebas
        private val REQUEST_CODE_PERMISSION = 68
        private val CAMERA_REQUEST_CAPTURE = 67


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portofolio)

        imageButton2.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel: 081338942027")
            }
            startActivity(intent)
        })

        imageButton3.setOnClickListener(View.OnClickListener {
            composeEmail(arrayOf("wiaryadhi@gmail.com"), "Test Email")
        })

        imageButton4.setOnClickListener(View.OnClickListener {

            showMap(Uri.parse("geo:-6.286919,106.779013?q=Wisma+BCA+Pondok+Indah"))
        })

        imgCamera.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED && checkSelfPermission(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(android.Manifest.permission.CAMERA)
                    requestPermissions(permissions, REQUEST_CODE_PERMISSION)
                } else {
                    captureCamera()
                }
            }
        })


    }

    fun captureCamera() {
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startActivityForResult(takeCamera, CAMERA_REQUEST_CAPTURE)

    }

    fun composeEmail(addresses: Array<String>, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun showMap(geoLocation: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    /* digunakan untuk trigger aplikasi memunculkan notif ke
    user bahwa aplikasi sedang menggunakan kamera dll */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    captureCamera();
                } else {
                    Toast.makeText(this, "Maaf Permission Denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    fun saveImage(bitmap: Bitmap) {
        val tanggal = SimpleDateFormat("yyyyMMdd.HHmmss").format(Date())
        val extStorage =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
        val namaFile = extStorage+"/BCAF" + tanggal + ".png"
        var file: File? = null

        file = File(namaFile)
        file.createNewFile()

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
        val bitmapData = bos.toByteArray()

        val fos = FileOutputStream(file)
        fos.write(bitmapData)
        fos.flush()
        fos.close()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CAPTURE && resultCode == RESULT_OK) {
            val bitmapImage = data?.extras?.get("data") as Bitmap
            imageView2.setImageBitmap(bitmapImage)
            saveImage(bitmapImage)


        }

    }


}
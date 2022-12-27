package com.bcafinance.training

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_menu.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

class MenuActivity : AppCompatActivity() {

    var username: String = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val datas: Bundle? = intent.extras
        username = datas?.getString("username", "").toString()
        password = datas?.getString("password", "").toString()
        selamatDatangText.text = "Selamat Datang $username"
        animateText()

        buttonTgl.setOnClickListener(View.OnClickListener {
            datePicker(it)
        })

        btnDial.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel: 081338942027")
            }
            startActivity(intent)
        })
    }

    fun animateText() {
        val anim = AlphaAnimation(0.0f, 1f)
        anim.duration = 50
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        selamatDatangText.startAnimation(anim)
    }

    fun datePicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker,
                year: Int,
                monthOfYear: Int,
                dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MMM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
                textTahun.text = sdf.format(c.getTime())

                val different = Period.between(LocalDate.of(year, month-1, dayOfMonth), LocalDate.now())
                outUmur.setText("Umur anda adalah ${different.years} tahun ${different.toTotalMonths() % 12 } bulan")
            }
        }

        DatePickerDialog(this, datSetListener,
        c.get(Calendar.YEAR),
        c.get(Calendar.MONTH),
        c.get(Calendar.DAY_OF_MONTH)).show()

    }


}
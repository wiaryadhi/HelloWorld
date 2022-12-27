package com.bcafinance.training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val defPass = "12345"
    val defUser = "arya"

    var counter = 0

    fun init() {

        buttonLogin.setOnClickListener(View.OnClickListener {
//            checkPassword(it)

//            var buttonNew = Button(applicationContext)
//            buttonNew.text = "Button" + counter++
//            containerLinear.addView(buttonNew)
//        })
//
//        textForgot.setOnClickListener(View.OnClickListener {
//            clickForgot(it)
//        })
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("username", textUsername.text.toString())
            intent.putExtra("password", textPassword.text.toString())
            startActivity(intent)
        })
    }
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
}

//

//
//    fun checkPassword(v: View) {
//        if (textUsername.text.contentEquals(defUser) && textPassword.text.contentEquals(defPass)) {
//            Toast.makeText(
//                applicationContext,
//                "Selamat anda berhasil uhuy",
//                Toast.LENGTH_LONG
//            ).show()
//        } else {
//            Toast.makeText(
//                applicationContext,
//                "Login Gagal",
//                Toast.LENGTH_LONG
//            ).show()
//
//        }
//    }
//
//    fun clickForgot(v: View) {
//        Toast.makeText(
//            applicationContext,
//            "Form Forgot Password terbuka",
//            Toast.LENGTH_LONG
//        ).show()
//    }
//}
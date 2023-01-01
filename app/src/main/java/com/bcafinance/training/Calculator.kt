package com.bcafinance.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_calculator.*
import org.mariuszgromada.math.mxparser.Expression


class Calculator : AppCompatActivity() {
    var lastDigit: Boolean = true
    var lastComa: Boolean = true
    var lastOperator: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)


    }

    fun onDigitPress(view: View) {
        inputText.append((view as Button).text)
        lastDigit = true
        lastComa = false
    }

    fun onOperatorPress(view: View) {
        if(lastDigit && !lastComa){
            inputText.append((view as Button).text)
            lastDigit = false
            lastComa = false
            lastOperator = true
        }

    }

    fun onClear(view: View) {
        inputText.setText("")
    }

    fun result(view: View) {
        var e = Expression(inputText.text.toString())
        inputText.setText(e.calculate().toString())
    }

    fun another(view: View) {
        if(lastDigit){
            inputText.append((view as Button).text)
            lastComa = true
        }

    }

}

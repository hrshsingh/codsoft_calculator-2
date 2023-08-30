package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView:TextView
    private lateinit var resul: TextView
    private var resu = 0.0
    private var currentExpression =""
    private fun handleButtonClick(text: String) {
        currentExpression += text
        updateResultTextView()

    }

    private fun updateResultTextView() {
        resultTextView.text = currentExpression
        resul.text = resu.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTextView = findViewById(R.id.result_text_view)
        resul = findViewById(R.id.result)

        val button = listOf(
            R.id.btn_zero,R.id.btn_one,R.id.btn_two,R.id.btn_three,R.id.btn_four,R.id.btn_five,R.id.btn_six,R.id.btn_seven,R.id.btn_eight,R.id.btn_nine,R.id.btn_dot,R.id.btn_divide,R.id.btn_multiple,R.id.btn_plus,R.id.btn_percentage,R.id.btn_minus
        )
        button.forEach { id->
            findViewById<Button>(id).setOnClickListener {
                handleButtonClick(findViewById<Button>(id).text.toString())
            }
        }
        findViewById<Button>(R.id.btn_small_bracket).setOnClickListener {
            currentExpression += if (currentExpression.endsWith("(")) ")" else "("
            updateResultTextView()
        }
        findViewById<Button>(R.id.btn_delete).setOnClickListener {
            currentExpression=""
            updateResultTextView()
        }
        findViewById<Button>(R.id.btn_back).setOnClickListener {
        if(currentExpression.isNotEmpty()){
            currentExpression = currentExpression.substring(0,currentExpression.length-1)
            updateResultTextView()
        }}
        findViewById<Button>(R.id.btn_equal).setOnClickListener {
            calculateResult()

        }
    }

    private fun calculateResult() {
        try {
            val expression = currentExpression
            val result =  ExpressionBuilder(currentExpression).build().evaluate()
            resu = result
            currentExpression = result.toString()
            updateResultTextView()
        } catch (e: Exception) {
            // If there is an error in the expression, display an error message
            currentExpression = "Error"
            updateResultTextView()

        }
    }



}
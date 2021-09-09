package com.example.calculator_bylee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator_bylee.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var str = ""
    var result = ""
    lateinit var binding: ActivityMainBinding

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("savedStr", str)
        outState.putString("savedResult", result)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedStr = savedInstanceState.getString("savedStr","")
        val savedResult = savedInstanceState.getString("savedResult", "")
        str = savedStr
        result = savedResult
        fillField(addSymbol = "")
        resultPrint(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            fillField(addSymbol = "0", existResult = "")
        }

        binding.button1.setOnClickListener {
            fillField(addSymbol = "1", existResult = "")
        }

        binding.button2.setOnClickListener {
            fillField(addSymbol = "2", existResult = "")
        }

        binding.button3.setOnClickListener {
            fillField(addSymbol = "3", existResult = "")
        }

        binding.button4.setOnClickListener {
            fillField(addSymbol = "4", existResult = "")
        }

        binding.button5.setOnClickListener {
            fillField(addSymbol = "5", existResult = "")
        }

        binding.button6.setOnClickListener {
            fillField(addSymbol = "6", existResult = "")
        }

        binding.button7.setOnClickListener {
            fillField(addSymbol = "7", existResult = "")
        }

        binding.button8.setOnClickListener {
            fillField(addSymbol = "8", existResult = "")
        }

        binding.button9.setOnClickListener {
            fillField(addSymbol = "9", existResult = "")
        }

        binding.buttonPoint.setOnClickListener {
            fillField(addSymbol = ".", existResult = "")
        }

        binding.buttonC.setOnClickListener {
            fillField(addSymbol = "", existStr = "", existResult = "")
        }

        binding.buttonDel.setOnClickListener {
            if (!str.isEmpty()) {
                str = str.substring(0, str.length - 1)
                fillField(addSymbol = "", existResult = "")
            }
        }

        binding.buttonPlus.setOnClickListener {
            isPreviousNumber(addSymbol = "+")
        }

        binding.buttonMinus.setOnClickListener {
            isPreviousNumber(addSymbol = "-")
        }

        binding.buttonMultiply.setOnClickListener {
            isPreviousNumber(addSymbol = "*")
        }

        binding.buttonDivide.setOnClickListener {
            isPreviousNumber(addSymbol = "/")
        }

        binding.buttonOpen.setOnClickListener {
            isPreviousSign(addSymbol = "(")
        }

        binding.buttonClose.setOnClickListener {
            if (str.contains('(') && !str[str.lastIndex].equals('(')) {
                isPreviousNumber(addSymbol = ")")
            }
        }

        binding.buttonEquals.setOnClickListener {
            try {
                result = ExpressionBuilder(str).build().evaluate().toString()
                resultPrint(result)
            } catch (e: Exception) {
                Log.d("ErrLog", "Error: ${e.message}")
                fillField(existResult = "")
            }
        }
    }

    fun fillField(addSymbol: String = "", existStr: String = str, existResult : String = result) {
        str = "${existStr}${addSymbol}"
        binding.textViewEnter.text = str
        result = existResult
        binding.textViewResult.text = result
    }

    fun isPreviousNumber(addSymbol: String) {
        if (!str.isEmpty() && !str[str.lastIndex].equals('+') && !str[str.lastIndex].equals('-') && !str[str.lastIndex].equals('*') && !str[str.lastIndex].equals('/')) {
            fillField(addSymbol, existResult = "")
        }
    }

    fun isPreviousSign(addSymbol: String) {
        if (str.isEmpty() || str[str.lastIndex].equals('+') || str[str.lastIndex].equals('-') || str[str.lastIndex].equals('*') || str[str.lastIndex].equals('/')) {
            fillField(addSymbol, existResult = "")
        }
    }

    fun resultPrint(number : String) {
        var roundedNumber = ""

        if(number.length > 16) roundedNumber = number.substring(0,15)
        else roundedNumber = number

        if (roundedNumber[roundedNumber.lastIndex].equals('0')) {
            val intNumber = roundedNumber.substring(0, number.length - 2)
            binding.textViewResult.text = intNumber
        } else {
            binding.textViewResult.text = roundedNumber
        }
    }
}




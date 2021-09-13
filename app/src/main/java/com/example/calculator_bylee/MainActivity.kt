package com.example.calculator_bylee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator_bylee.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private var str = ""
    private var resultDouble = 0.0
    private var resultBoolean = false
    lateinit var binding: ActivityMainBinding

    companion object {
        const val SAVED_STR = "savedStr"
        const val SAVED_RESULT_DOUBLE = "savedResultDouble"
        const val SAVED_RESULT_BOOLEAN = "savedResultBoolean"
        const val LOG_NAME = "MyLog"
        const val MAX_STR_LENGTH = 15
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            fillFields(addSymbol = "0", existStr = str, existResult = "")
        }

        binding.button1.setOnClickListener {
            fillFields(addSymbol = "1", existStr = str, existResult = "")
        }

        binding.button2.setOnClickListener {
            fillFields(addSymbol = "2", existStr = str, existResult = "")
        }

        binding.button3.setOnClickListener {
            fillFields(addSymbol = "3", existStr = str, existResult = "")
        }

        binding.button4.setOnClickListener {
            fillFields(addSymbol = "4", existStr = str, existResult = "")
        }

        binding.button5.setOnClickListener {
            fillFields(addSymbol = "5", existStr = str, existResult = "")
        }

        binding.button6.setOnClickListener {
            fillFields(addSymbol = "6", existStr = str, existResult = "")
        }

        binding.button7.setOnClickListener {
            fillFields(addSymbol = "7", existStr = str, existResult = "")
        }

        binding.button8.setOnClickListener {
            fillFields(addSymbol = "8", existStr = str, existResult = "")
        }

        binding.button9.setOnClickListener {
            fillFields(addSymbol = "9", existStr = str, existResult = "")
        }

        binding.buttonPoint.setOnClickListener {
            if(!str[str.lastIndex].equals('.')) {
                fillFields(addSymbol = ".", existStr = str, existResult = "")
            }
        }

        binding.buttonC.setOnClickListener {
            fillFields(addSymbol = "", existStr = "", existResult = "")
        }

        binding.buttonDel.setOnClickListener {
            if (str.isNotEmpty()) {
                str = str.substring(0, str.length - 1)
                fillFields(addSymbol = "", existStr = str, existResult = "")
            }
        }

        binding.buttonPlus.setOnClickListener {
            isPreviousNumber(addSymbol_ = "+")
        }

        binding.buttonMinus.setOnClickListener {
            isPreviousNumber(addSymbol_ = "-")
        }

        binding.buttonMultiply.setOnClickListener {
            isPreviousNumber(addSymbol_ = "*")
        }

        binding.buttonDivide.setOnClickListener {
            isPreviousNumber(addSymbol_ = "/")
        }

        binding.buttonOpen.setOnClickListener {
            isPreviousSign(addSymbol_ = "(")
        }

        binding.buttonClose.setOnClickListener {
            if (str.contains('(') && !str[str.lastIndex].equals('(') && !str[str.lastIndex].equals(')')) {
                isPreviousNumber(addSymbol_ = ")")
            }
        }

        binding.buttonEquals.setOnClickListener {
            calculate()
            if(resultBoolean) printResult(resultDouble)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVED_STR, str)
        outState.putDouble(SAVED_RESULT_DOUBLE, resultDouble)
        outState.putBoolean(SAVED_RESULT_BOOLEAN, resultBoolean)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        str = savedInstanceState.getString(SAVED_STR,"")
        resultDouble = savedInstanceState.getDouble(SAVED_RESULT_DOUBLE, 0.0)
        resultBoolean = savedInstanceState.getBoolean(SAVED_RESULT_BOOLEAN, false)
        fillFields(addSymbol = "", existStr = str, existResult = "")
        if(resultBoolean) printResult(resultDouble)
    }

    private fun fillFields(addSymbol: String = "", existStr: String = str, existResult : String = resultDouble.toString()) {
        str = "${existStr}${addSymbol}"
        binding.textViewEnter.text = str
        binding.textViewResult.text = existResult
    }

    private fun isPreviousNumber(addSymbol_: String) {
        if (!str.isEmpty() && !str[str.lastIndex].equals('+') && !str[str.lastIndex].equals('-') && !str[str.lastIndex].equals('*') && !str[str.lastIndex].equals('/')) {
            fillFields(addSymbol = addSymbol_, existResult = "")
        }
    }

    private fun isPreviousSign(addSymbol_: String) {
        if (str.isEmpty() || str[str.lastIndex].equals('+') || str[str.lastIndex].equals('-') || str[str.lastIndex].equals('*') || str[str.lastIndex].equals('/')) {
            fillFields(addSymbol = addSymbol_, existResult = "")
        }
    }

    private fun calculate() {
        try {
            resultDouble = ExpressionBuilder(str).build().evaluate()
            Log.d(LOG_NAME, "$resultDouble")
            resultBoolean = true
        } catch (e: Exception) {
            Log.d(LOG_NAME, "Error: ${e.message}")
            fillFields(existResult = "")
            resultBoolean = false
        }
    }

    private fun printResult(numberDouble: Double) {
        var numberString = numberDouble.toBigDecimal().toPlainString()
        when {
            numberDouble / 10.0.pow(MAX_STR_LENGTH) > 1 -> {
                numberString = getString(R.string.long_number)
            }
            numberString.length > MAX_STR_LENGTH && !numberString[MAX_STR_LENGTH-1].equals('.') -> {
                numberString = numberString.substring(0, MAX_STR_LENGTH)
            }
            numberString.length > MAX_STR_LENGTH && numberString[MAX_STR_LENGTH-1].equals('.') -> {
                numberString = numberString.substring(0, MAX_STR_LENGTH-1)
            }
            numberString[numberString.lastIndex].equals('0') -> {
                numberString = numberString.substring(0, numberString.length - 2)
            }
        }
        fillFields(existResult = numberString)
    }

}




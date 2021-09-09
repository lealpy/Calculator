package com.example.calculator_bylee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator_bylee.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var str = ""
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            fillField("0")
        }

        binding.button1.setOnClickListener {
            fillField("1")
        }

        binding.button2.setOnClickListener {
            fillField("2")
        }

        binding.button3.setOnClickListener {
            fillField("3")
        }

        binding.button4.setOnClickListener {
            fillField("4")
        }

        binding.button5.setOnClickListener {
            fillField("5")
        }

        binding.button6.setOnClickListener {
            fillField("6")
        }

        binding.button7.setOnClickListener {
            fillField("7")
        }

        binding.button8.setOnClickListener {
            fillField("8")
        }

        binding.button9.setOnClickListener {
            fillField("9")
        }

        binding.buttonPoint.setOnClickListener {
            if (str.isEmpty() || !str.contains('.')) {
                fillField(".")
            }
        }

        binding.buttonC.setOnClickListener {
            fillField("", "")
            binding.textViewResult.text = ""
        }

        binding.buttonDel.setOnClickListener {
            if (!str.isEmpty()) {
                str = str.substring(0, str.length - 1)
                binding.textViewEnter.text = "$str"
                binding.textViewResult.text = ""
            }
        }

        binding.buttonPlus.setOnClickListener {
            isPreviousNumber("+")
        }

        binding.buttonMinus.setOnClickListener {
            isPreviousNumber("-")
        }

        binding.buttonMultiply.setOnClickListener {
            isPreviousNumber("*")
        }

        binding.buttonDivide.setOnClickListener {
            isPreviousNumber("/")
        }

        binding.buttonOpen.setOnClickListener {
            isPreviousSign("(")
        }

        binding.buttonClose.setOnClickListener {
            if (str.contains('(') && !str[str.lastIndex].equals('(')) {
                isPreviousNumber(")")
            }
        }

        binding.buttonEquals.setOnClickListener {
            try {
                var result = ExpressionBuilder(str).build().evaluate().toString()
                integerNotation(result)
            } catch (e: Exception) {
                Log.d("ErrLog", "Error: ${e.message}")
                binding.textViewResult.text = ""
            }
        }

    }

    fun fillField(addSymbol: String, existStr: String = str) {
        str = "${existStr}${addSymbol}"
        binding.textViewEnter.text = "$str"
    }

    fun isPreviousNumber(addSymbol: String) {
        if (!str.isEmpty() && !str[str.lastIndex].equals('+') && !str[str.lastIndex].equals('-') && !str[str.lastIndex].equals(
                '*') && !str[str.lastIndex].equals('/')
        ) {
            fillField(addSymbol)
        }
    }

    fun isPreviousSign(addSymbol: String) {
        if (str.isEmpty() || str[str.lastIndex].equals('+') || str[str.lastIndex].equals('-') || str[str.lastIndex].equals(
                '*') || str[str.lastIndex].equals('/')
        ) {
            fillField(addSymbol)
        }
    }

    fun integerNotation(number : String) {
        if(number[number.lastIndex].equals('0'))
        {
            val intNumber = number.substring(0, number.length - 2)
            binding.textViewResult.text = "$intNumber"
        } else {
            binding.textViewResult.text = "$number"
        }
    }

}




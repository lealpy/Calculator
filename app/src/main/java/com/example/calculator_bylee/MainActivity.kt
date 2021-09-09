package com.example.calculator_bylee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator_bylee.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var str = ""
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            str = "${str}0"
            binding.textViewEnter.text = "$str"
        }

        binding.button1.setOnClickListener {
            str = "${str}1"
            binding.textViewEnter.text = "$str"
        }

        binding.button2.setOnClickListener {
            str = "${str}2"
            binding.textViewEnter.text = "$str"
        }

        binding.button3.setOnClickListener {
            str = "${str}3"
            binding.textViewEnter.text = "$str"
        }

        binding.button4.setOnClickListener {
            str = "${str}4"
            binding.textViewEnter.text = "$str"
        }

        binding.button5.setOnClickListener {
            str = "${str}5"
            binding.textViewEnter.text = "$str"
        }

        binding.button6.setOnClickListener {
            str = "${str}6"
            binding.textViewEnter.text = "$str"
        }

        binding.button7.setOnClickListener {
            str = "${str}7"
            binding.textViewEnter.text = "$str"
        }

        binding.button8.setOnClickListener {
            str = "${str}8"
            binding.textViewEnter.text = "$str"
        }

        binding.button9.setOnClickListener {
            str = "${str}9"
            binding.textViewEnter.text = "$str"
        }

        binding.buttonPoint.setOnClickListener {
            if(str.isEmpty() || !str[str.lastIndex].equals('.'))
            str = "${str}."
            binding.textViewEnter.text = "$str"
        }

        binding.buttonC.setOnClickListener {
            str = ""
            binding.textViewEnter.text = "$str"
            binding.textViewResult.text = ""
        }

        binding.buttonDel.setOnClickListener {
            if(!str.isEmpty()) {
                str = str.substring(0, str.length - 1)
                binding.textViewEnter.text = "$str"
                binding.textViewResult.text = ""
            }
        }

        binding.buttonPlus.setOnClickListener {
            //Не может быть записано два арифметических знака подряд, знак не может идти в начале строки
            if(!str.isEmpty() && (!str[str.lastIndex].equals('+') || !str[str.lastIndex].equals('-') || !str[str.lastIndex].equals('*') || !str[str.lastIndex].equals('/'))) {
                str = "${str}+"
                binding.textViewEnter.text = "$str"
            }
        }

        binding.buttonMinus.setOnClickListener {
            //Не может быть записано два арифметических знака подряд, знак не может идти в начале строки
            if(!str.isEmpty() && (!str[str.lastIndex].equals('+') || !str[str.lastIndex].equals('-') || !str[str.lastIndex].equals('*') || !str[str.lastIndex].equals('/'))) {
                str = "${str}-"
                binding.textViewEnter.text = "$str"
            }
        }

        binding.buttonMultiply.setOnClickListener {
            //Не может быть записано два арифметических знака подряд, знак не может идти в начале строки
            if(!str.isEmpty() && (!str[str.lastIndex].equals('+') || !str[str.lastIndex].equals('-') || !str[str.lastIndex].equals('*') || !str[str.lastIndex].equals('/'))) {
                str = "${str}*"
                binding.textViewEnter.text = "$str"
            }
        }

        binding.buttonDivide.setOnClickListener {
            //Не может быть записано два арифметических знака подряд, знак не может идти в начале строки
            if(!str.isEmpty() && (!str[str.lastIndex].equals('+') || !str[str.lastIndex].equals('-') || !str[str.lastIndex].equals('*') || !str[str.lastIndex].equals('/'))) {
                str = "${str}/"
                binding.textViewEnter.text = "$str"
            }
        }

        binding.buttonOpen.setOnClickListener {
            //Скобка должна открываться после арифметических знаков
            if(str.isEmpty() || str[str.lastIndex].equals('+') || str[str.lastIndex].equals('-') || str[str.lastIndex].equals('*') || str[str.lastIndex].equals('/')) {
                str = "${str}("
                binding.textViewEnter.text = "$str"
            }
        }

        binding.buttonClose.setOnClickListener {
            // Скобка не может закрываться после арифметических знаков, не может открываться в начале строки
            if(!str.isEmpty() && (!str[str.lastIndex].equals('+') || !str[str.lastIndex].equals('-') || !str[str.lastIndex].equals('*') || !str[str.lastIndex].equals('/'))) {
                str = "${str})"
                binding.textViewEnter.text = "$str"
            }
        }

        binding.buttonEquals.setOnClickListener {
            try {
                val ex = ExpressionBuilder(str).build()
                var result = ex.evaluate().toString()
                //Удалим ".0", если результат - целое число
                if(result[result.lastIndex].equals('0')) {
                    result = result.substring(0,result.length-2)
                    binding.textViewResult.text = "$result"
                } else {
                    binding.textViewResult.text = "$result"
                }
            } catch (e:Exception) {
                Log.d("Ошибка", "сообщение: ${e.message}")
            }
        }

    }

}




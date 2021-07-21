package com.example.xml_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.xml_test.databinding.ActivityMainBinding
import java.lang.Math.ceil
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calcButton.setOnClickListener{calculateTip()}



    }
    fun calculateTip()
    {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull() //toDouble is called on a string
        if(cost==null){
            binding.tipResult.text = " "
            return
        }
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val tipPercentage = when(selectedId){
            R.id.amazing_radio -> 0.20
            R.id.good_radio -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tipResult,formattedTip)

    }
}
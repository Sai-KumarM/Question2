package com.example.question2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerFruits: Spinner
    private lateinit var buttonShowInfo: Button
    private lateinit var textFruitInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerFruits = findViewById(R.id.spinnerFruits)
        buttonShowInfo = findViewById(R.id.buttonShowInfo)
        textFruitInfo = findViewById(R.id.textFruitInfo)

        val fruits = arrayOf("Apple", "Banana", "Grapes", "Orange", "Pineapple")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, fruits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFruits.adapter = adapter

        // Spinner OnItemSelectedListener
        spinnerFruits.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedFruit = parent?.getItemAtPosition(position).toString()
                val info = getFruitInfo(selectedFruit)
                textFruitInfo.text = info
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                textFruitInfo.text = ""
            }
        }

        // Button OnClickListener
        buttonShowInfo.setOnClickListener {
            val selectedFruit = spinnerFruits.selectedItem.toString()
            val info = getFruitInfo(selectedFruit)
            showToast(info)
        }
    }

    private fun getFruitInfo(fruit: String): String {
        return when (fruit) {
            "Apple" -> "Apples are rich in fiber and vitamin C."
            "Banana" -> "Bananas are a good source of potassium and provide quick energy."
            "Grapes" -> "Grapes has lot of antioxidants."
            "Orange" -> "Oranges are rich in Vitamin C and citric acid."
            "Pineapple" -> "Pineapples are rich in antioxidants and help boost immune system."
            else -> ""
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
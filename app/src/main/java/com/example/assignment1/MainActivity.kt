package com.example.assignment1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etHeight: EditText
    private lateinit var etWeight: EditText
    private lateinit var spinnerGender: Spinner
    private lateinit var btnCalculateBMI: Button
    private lateinit var tvResult: TextView
    //private lateinit var tvBMI: TextView // Add reference to the new TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etHeight = findViewById(R.id.etHeight)
        etWeight = findViewById(R.id.etWeight)
        spinnerGender = findViewById(R.id.spinnerGender)
        btnCalculateBMI = findViewById(R.id.btnCalculateBMI)
        tvResult = findViewById(R.id.tvResult)
        //tvBMI = findViewById(R.id.bmi) // Initialize the new TextView

        // Set up the spinner with gender options
        val genderOptions = arrayOf("Male", "Female")
        spinnerGender.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, genderOptions)

        btnCalculateBMI.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val heightCm = etHeight.text.toString().toFloatOrNull()
        val weightKg = etWeight.text.toString().toFloatOrNull()

        if (heightCm == null || weightKg == null) {
            tvResult.text = "Please enter valid height and weight."
            return
        }

        val bmi = weightKg / ((heightCm / 100) * (heightCm / 100))
        val bmiCategory = getBMICategory(bmi)
        val gender = spinnerGender.selectedItem.toString()

        val resultText = "BMI: %.2f\nCategory: %s".format(bmi, bmiCategory)

        if (gender == "Male") {
            val resultText = "Male BMI: %.2f\nCategory: %s".format(bmi, bmiCategory)
        //tvBMI.text = "BMI (Male): %.2f".format(bmi)
        } else if (gender == "Female") {
            //tvBMI.text = "BMI (Female): %.2f".format(bmi)
            val resultText = "Female BMI: %.2f\nCategory: %s".format(bmi, bmiCategory)
        }

        tvResult.text = resultText
    }


    private fun getBMICategory(bmi: Float): String {
        return when {
            bmi < 16 -> "Severely Underweight"
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }
    }
}

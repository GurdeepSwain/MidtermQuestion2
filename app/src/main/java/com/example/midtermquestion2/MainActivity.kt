package com.example.midtermquestion2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var fruitSpinner: Spinner
    private lateinit var selectButton: Button
    private lateinit var fruitImageView: ImageView
    private var selectedFruit: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fruitSpinner = findViewById(R.id.fruitSpinner)
        selectButton = findViewById(R.id.selectButton)
        fruitImageView = findViewById(R.id.fruitImageView)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.fruit_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            fruitSpinner.adapter = adapter
        }

        fruitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedFruit = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        selectButton.setOnClickListener {
            val imageResource = when (selectedFruit) {
                "Apple" -> R.drawable.apple
                "Banana" -> R.drawable.banana
                "Cherry" -> R.drawable.cherry
                "Elderberry" -> R.drawable.elderberry
                "Fig" -> R.drawable.fig
                "Grape" -> R.drawable.grape
                else -> 0
            }
            fruitImageView.setImageResource(imageResource)
        }

        val intentData = intent.getStringExtra("extra_data")
        if (intentData != null) {
            selectButton.text = "Received: $intentData"
        }
    }
}





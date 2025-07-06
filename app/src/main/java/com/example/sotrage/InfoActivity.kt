package com.example.sotrage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sotrage.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textTitle.text = "sotrage"
        binding.textDescription.text = "This app allows you to adjust settings and view app info. Enjoy!"
    }
}

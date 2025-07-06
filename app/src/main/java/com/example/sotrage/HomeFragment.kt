package com.example.sotrage

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.sotrage.databinding.FragmentHomeBinding
import net.objecthunter.exp4j.ExpressionBuilder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var expression = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnShowInfo.setOnClickListener {
            startActivity(Intent(requireContext(), InfoActivity::class.java))
        }

        setupCalculator()
        return binding.root
    }

    private fun setupCalculator() {
        val display = binding.textViewInput

        for (i in 0 until binding.gridButtons.childCount) {
            val button = binding.gridButtons.getChildAt(i) as Button
            button.setOnClickListener {
                when (val value = button.text.toString()) {
                    "=" -> {
                        try {
                            val expr = ExpressionBuilder(expression).build()
                            val result = expr.evaluate()
                            val resultText = if (result == result.toLong().toDouble()) {
                                result.toLong().toString()
                            } else {
                                result.toString()
                            }
                            display.text = resultText
                            expression = resultText
                        } catch (e: Exception) {
                            display.text = getString(R.string.error_text)
                            expression = ""
                        }
                    }
                    "C" -> {
                        expression = ""
                        display.text = getString(R.string.zero_text)
                    }
                    else -> {
                        expression += value
                        display.text = expression
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

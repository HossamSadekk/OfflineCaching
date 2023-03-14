package com.example.offlinecaching.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.offlinecaching.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val characterAdapter = CharacterAdapter()

        binding?.apply {
            recyclerViewCharacters.apply {
                adapter = characterAdapter
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { uiState ->
                    if (uiState.isLoading) {
                        binding?.apply {
                            progressCircular.visibility = View.VISIBLE
                        }
                    }
                    if (!uiState.gotCharacters.isEmpty()) {
                        Log.d("hossam", uiState.gotCharacters.toString())

                        binding?.apply {
                            progressCircular.visibility = View.INVISIBLE
                        }
                        characterAdapter.submitList(uiState.gotCharacters)
                    }
                    if (uiState.error.isNotEmpty()) {
                        Log.d("error", uiState.error.toString())
                        binding?.apply {
                            progressCircular.visibility = View.INVISIBLE
                            errorTxt.visibility = View.VISIBLE
                            errorTxt.text = uiState.error
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
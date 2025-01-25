package com.example.carapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.carapp.common.viewModel
import com.example.carapp.di.injector
import com.example.carapp.ui.screens.CarListScreen
import com.example.carapp.ui.theme.CarAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: CarViewModel by viewModel { injector.carViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarListScreen(viewModel)
                }
            }
        }
    }
}




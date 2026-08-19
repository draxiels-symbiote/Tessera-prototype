package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ui.navigation.AppNavigation
import com.example.ui.theme.TesseraTheme
import com.example.ui.theme.TesseraThemeHelper
import com.example.ui.viewmodel.TesseraViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: TesseraViewModel by viewModels {
        TesseraViewModel.Factory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val userProfile by viewModel.userProfile.collectAsState()
            val selectedTheme = userProfile?.selectedTheme ?: "Obsidian Rose"

            TesseraTheme(selectedTheme = selectedTheme) {
                val colors = TesseraThemeHelper.colors
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colors.bg
                ) {
                    val navController = rememberNavController()
                    AppNavigation(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

package com.theophiluskibet.coopbank.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.theophiluskibet.coopbank.presentation.navigation.AppNavigation
import com.theophiluskibet.coopbank.presentation.ui.theme.CoopBankTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoopBankTheme {
                AppNavigation()
            }
        }
    }
}
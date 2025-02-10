package com.mobileexam.tabingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mobileexam.tabingo.functions.RicknMortyApp
import com.mobileexam.tabingo.ui.theme.TabingoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabingoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RicknMortyApp() // Calls the composable function to display the App
                }
            }
        }
    }
}
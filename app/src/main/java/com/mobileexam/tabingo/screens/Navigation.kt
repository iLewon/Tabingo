package com.mobileexam.tabingo.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobileexam.tabingo.model.CharViewModel
import com.mobileexam.tabingo.model.CharactersUiState

@Composable
fun AppStart() {
    val charsViewModel: CharViewModel = viewModel(factory = CharViewModel.Factory)
    val charViewModel: CharViewModel = viewModel()
    val navHostController: NavHostController = rememberNavController()
    var appBarTitle by remember { mutableStateOf("") } // Default title

    Scaffold(
        topBar = {
            RickAndMortyAppBar(
                title = appBarTitle,
                showBackButton = navHostController.currentBackStackEntry?.destination?.route != "mainScreen",
                onBackPressed = {navHostController.popBackStack()}
            )
        }
    ) { innerPadding ->
        NavHost(navController = navHostController,startDestination = "mainScreen",modifier = Modifier.padding(innerPadding)) {

            composable("mainScreen") {
                appBarTitle = "Rick And Morty Characters"
                MainScreen(
                    characterUiState = charViewModel.charUiState,
                    navController = navHostController,
                )
            }
            composable("DetailsScreen/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId")?.toInt()
                val character = charsViewModel.charUiState.let { state ->
                    if (state is CharactersUiState.Success) {
                        state.characters.results.find { it.id == characterId }
                    } else null
                }

                character?.let {appBarTitle = it.name
                    CharacterDetails(character = it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyAppBar(
    title: String,
    showBackButton: Boolean,
    onBackPressed: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title,style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold)
                },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        }
    )
}
package com.mobileexam.tabingo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobileexam.tabingo.R
import com.mobileexam.tabingo.model.CharactersUiState

@Composable
fun MainScreen(
    characterUiState: CharactersUiState,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    when (characterUiState) {
        is CharactersUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is CharactersUiState.Success -> RickAndMortyApp(
            characters = characterUiState.characters.results,
            navController = navController,
            modifier = modifier.fillMaxSize()
        )
        is CharactersUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "Loading Failed", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "Loading..."
    )
}
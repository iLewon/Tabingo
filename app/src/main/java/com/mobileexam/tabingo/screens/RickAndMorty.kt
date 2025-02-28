package com.mobileexam.tabingo.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mobileexam.tabingo.R
import com.mobileexam.tabingo.data.Character

@Composable
fun RickAndMortyApp(
    characters: List<Character>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(items = characters, key = { character -> character.id }) { character ->
            CharacterCard(character = character, navController = navController) // Pass the navController to each CharacterCard
        }
    }
}
@Composable
fun CharacterCard(
    character: Character,
    navController: NavController,
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {navController.navigate("DetailsScreen/${character.id}")},
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(character.image).crossfade(true).build(),
                contentDescription = "Character Photo",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(80.dp) // Adjusted size for better proportion
                    .clip(RoundedCornerShape(8.dp)), // Adds rounded corners to the image
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
            )
            Column(
                modifier = Modifier.padding(20.dp),verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold // Make text bold
                )
                Text(text = character.status, style = MaterialTheme.typography.bodyMedium)
                Text(text = character.species, style = MaterialTheme.typography.bodyMedium)
                Text(text = character.type, style = MaterialTheme.typography.bodyMedium)
            }
        }


        Column(
            modifier = Modifier.animateContentSize(),
        ) {

            Text(
                text = "More Details...",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 15.sp),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clickable { expanded = !expanded }
            )
        }
        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = character.origin.name, style = MaterialTheme.typography.bodyMedium)
            Text(text = character.location.name, style = MaterialTheme.typography.bodyMedium)
            Text(text = character.gender, style = MaterialTheme.typography.bodyMedium)

        }
    }
}

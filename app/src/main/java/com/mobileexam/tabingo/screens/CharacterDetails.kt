package com.mobileexam.tabingo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mobileexam.tabingo.R
import com.mobileexam.tabingo.data.Character

@Composable
fun CharacterDetails(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = "Character Photo",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold, // Make text bold
                    modifier = Modifier.align(Alignment.CenterHorizontally) // Center text
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Gender: ${character.gender}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Status: ${character.status}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Species: ${character.species}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = if (character.type.isEmpty()) "Type: None" else "Type: ${character.type}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

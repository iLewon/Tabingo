package com.mobileexam.tabingo.functions

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobileexam.tabingo.data.charData
import com.mobileexam.tabingo.model.CharModel
import com.mobileexam.tabingo.ui.theme.TabingoTheme

// Composable function representing the main UI of the app
@Composable
fun RicknMortyApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App title text
        Text(
            text = "Rick & Morty Characters",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // LazyColumn to efficiently display a list of characters
        LazyColumn {
            items(charData) { chars ->
                CharacterList(chars) // Displays each character in a card format
            }
        }
    }
}

// Composable function to display each character in a list with an expandable section
@Composable
private fun CharacterList(chars: CharModel) {
    var expanded by remember { mutableStateOf(false) } // Tracks whether details are expanded

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded } // Toggles expansion on click
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Displays the character's image
                Image(
                    painter = painterResource(id = chars.imageRes),
                    contentDescription = null, // No need for description as it's decorative
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(80.dp) // Adjusted size for better proportion
                        .clip(RoundedCornerShape(8.dp)), // Adds rounded corners to the image
                )

                // Displays the character's basic details
                Column {
                    Text(text = chars.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = chars.status, style = MaterialTheme.typography.bodyMedium)
                    Text(text = chars.species, style = MaterialTheme.typography.bodyMedium)
                    Text(text = chars.type, style = MaterialTheme.typography.bodyMedium)
                }
            }

            // Expandable section to show additional details
            Text(
                text = "More Details...",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 15.sp),
                modifier = Modifier
                    .padding(top = 10.dp) // Adds space above "More Details..."
                    .clickable { expanded = !expanded } // Click to expand/collapse details
            )

            // Displays extra information when expanded
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = chars.gender, style = MaterialTheme.typography.bodyMedium)
                Text(text = chars.origin, style = MaterialTheme.typography.bodyMedium)
                Text(text = chars.location, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MobileExamPreview() {
    TabingoTheme {
        RicknMortyApp()
    }
}
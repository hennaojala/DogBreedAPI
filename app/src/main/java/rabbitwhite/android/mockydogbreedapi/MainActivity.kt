package rabbitwhite.android.mockydogbreedapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import rabbitwhite.android.mockydogbreedapi.data.model.DogBreed
import rabbitwhite.android.mockydogbreedapi.viewmodel.DogBreedViewModel

// MainActivity is the entry point of the app, inheriting from ComponentActivity.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogBreedApp() // Call the main composable function.
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogBreedApp(dogBreedViewModel: DogBreedViewModel = viewModel()) {
    val dogBreeds by dogBreedViewModel.dogBreeds.collectAsState()
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Dog Breeds Information", textAlign = TextAlign.Center)
                },
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    IconButton(onClick = { navController.navigate("about") }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF3D5A80),
                    titleContentColor = Color.White
                )
            )
        },
    ) { padding ->
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(navController)
            }
            composable("about") {
                AboutScreen(navController)
            }
            composable("list") {
                DogBreedListScreen(dogBreeds, padding)
            }
        }
    }
}



@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.dog_background),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Button(
                onClick = { navController.navigate("list") },
                modifier = Modifier
                    .padding(start = 32.dp, top = 200.dp)
            ) {
                Text(text = "Go to DogBreed List")
            }
        }
    }
}
@Composable
fun DogBreedListScreen(dogBreeds: List<DogBreed>, padding: PaddingValues) {

    if (dogBreeds.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.dog_background),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0x80000000), Color(0x00000000))
                        )
                    )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .padding(12.dp)
            ) {
                items(dogBreeds) { breed ->
                    DogBreedCard(breed)
                }
            }
        }
    }
}


@Composable
fun DogBreedCard(breed: DogBreed) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF98C9E6)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFF1D3557)),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            InfoSection(title = "Origin", content = breed.origin)
            InfoSection(title = "Size", content = breed.size)
            InfoSection(title = "Coat", content = breed.coat)
            InfoSection(title = "Temperament", content = breed.temperament.joinToString(", "))
            InfoSection(title = "Life Expectancy", content = breed.lifeExpectancy)
            InfoSection(title = "Description", content = breed.description)

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun InfoSection(title: String, content: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color(0xFFE1EFFF))
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFF1D3557),

                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(4.dp),
        )

        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF1D3557)),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(start = 4.dp, end = 2.dp)

        )
    }
}
@Composable
fun AboutScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.about_phone),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color(0xFFF5F5DC).copy(alpha = 0.9f))
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Mocky Dog Breed API",
                    style = MaterialTheme.typography.headlineMedium.copy(color = Color(0xFF1D3557)),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Version 1.0.0",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF1D3557)),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "This app provides information about various dog breeds, including their origin, size, coat, temperament, and life expectancy.",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF1D3557)),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Author: Henna Ojala",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF1D3557)),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Published: 16.12.2024",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF1D3557)),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = { navController.navigate("home") },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Back to home")
                }
            }
        }
    }
}

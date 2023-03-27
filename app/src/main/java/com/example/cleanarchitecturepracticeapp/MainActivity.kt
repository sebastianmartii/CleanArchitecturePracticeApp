package com.example.cleanarchitecturepracticeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cleanarchitecturepracticeapp.feature_genres.presentation.GenresViewModel
import com.example.cleanarchitecturepracticeapp.ui.theme.CleanArchitecturePracticeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitecturePracticeAppTheme {
                val viewModel: GenresViewModel = hiltViewModel()
                val state = viewModel.state.value
                val genres by viewModel.genres.collectAsState(initial = emptyList())
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ) {values ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(values)
                    ){
                        TextButton(onClick = { viewModel.onGenerateGenre() }) {
                            Text(
                                text = stringResource(id = R.string.generateGenreButtonText),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        if (state.isLoading) {
                            CircularProgressIndicator()
                        } else {
                            Text(text = state.genre ?: "")
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.previousGenresListTitle),
                                fontWeight = FontWeight.Medium,
                            )
                            Button(onClick = { viewModel.onGenresDelete(genres) }) {
                                Text(text = stringResource(id = R.string.deleteButtonText))
                            }
                        }
                        Divider()
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyColumn(modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp))
                        {
                            items(genres.size) {
                                Text(text = genres[it].genre)
                            }
                        }
                    }
                }
            }
        }
    }
}

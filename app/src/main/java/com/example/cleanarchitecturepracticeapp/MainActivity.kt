package com.example.cleanarchitecturepracticeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cleanarchitecturepracticeapp.feature_activities.presentation.ActivityScreen
import com.example.cleanarchitecturepracticeapp.feature_genres.presentation.GenresScreen
import com.example.cleanarchitecturepracticeapp.ui.theme.CleanArchitecturePracticeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitecturePracticeAppTheme {
                var showActivityScreen by remember {
                    mutableStateOf(false)
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ) {values ->
                    Column(modifier = Modifier.fillMaxSize()){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(onClick = { showActivityScreen = true }) {
                                Text(text = "Activities")
                            }
                            TextButton(onClick = { showActivityScreen = false }) {
                                Text(text = "Genres")
                            }
                        }
                        AnimatedContent(targetState = showActivityScreen){
                            when(it){
                                false -> {
                                    GenresScreen(
                                        paddingValues = values
                                    )
                                }
                                else -> {
                                    ActivityScreen(
                                        paddingValues = values
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

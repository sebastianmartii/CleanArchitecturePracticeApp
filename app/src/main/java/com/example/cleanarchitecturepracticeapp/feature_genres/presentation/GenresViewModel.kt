package com.example.cleanarchitecturepracticeapp.feature_genres.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturepracticeapp.core.util.Resource
import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.entity.GenresEntity
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.use_case.GenresUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val genresUseCases: GenresUseCases
) : ViewModel() {
    val genres = genresUseCases.getAllGenres()

    private val _state = mutableStateOf(GenreState())
    val state: State<GenreState> = _state

    private var job: Job? = null

    fun onGenerateGenre() {
        job?.cancel()
        job = viewModelScope.launch{
            genresUseCases.getGenre().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            genre = result.data ?: "",
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            genre = result.data ?: "",
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            genre = result.data ?: "",
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun onGenresDelete(list: List<GenresEntity>) {
        viewModelScope.launch {
            genresUseCases.deleteGenres(list)
        }
    }
}
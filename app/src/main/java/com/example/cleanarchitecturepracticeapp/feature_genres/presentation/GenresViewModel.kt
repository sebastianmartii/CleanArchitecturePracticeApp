package com.example.cleanarchitecturepracticeapp.feature_genres.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturepracticeapp.core.util.Resource
import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.entity.GenresEntity
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.use_case.GenresUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val genresUseCases: GenresUseCases
) : ViewModel() {
    private val genres = genresUseCases.getAllGenres()

    private val _state = MutableStateFlow(GenreState())
    val state = combine(_state, genres) { state, genres ->
        state.copy(
            genres = genres
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), GenreState())

    fun onGenerateGenre() {
        viewModelScope.launch{
            genresUseCases.getGenre().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            genre = result.data ?: "",
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            genre = result.data ?: "",
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            genre = result.data ?: "",
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun onGenresDelete(genres: List<GenresEntity>) {
        viewModelScope.launch {
            genresUseCases.deleteGenres(genres)
        }
    }
}
package com.kc.android.rickandmorty.ui.characters

import androidx.lifecycle.ViewModel
import com.kc.android.rickandmorty.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    val characters = repository.getCharacters()
}

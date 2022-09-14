package com.kc.android.rickandmorty.ui.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.kc.android.rickandmorty.data.entities.CharacterItem
import com.kc.android.rickandmorty.data.repository.CharacterRepository
import com.kc.android.rickandmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val character: LiveData<Resource<CharacterItem>> = _character

    fun start(id: Int) {
        _id.value = id
    }
}

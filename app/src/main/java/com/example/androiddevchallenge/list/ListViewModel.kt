package com.example.androiddevchallenge.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.repository.PetsRepo
import kotlinx.coroutines.launch

/**
 * @auther zhangduo on 2021/3/2
 */
class ListViewModel(private val petsRepo: PetsRepo) : ViewModel() {

    private val _pets = MutableLiveData<List<Pet>>()
    val pets = _pets as LiveData<List<Pet>>

    fun loadPets() {
        viewModelScope.launch {
            _pets.value = petsRepo.getList()
        }
    }
}
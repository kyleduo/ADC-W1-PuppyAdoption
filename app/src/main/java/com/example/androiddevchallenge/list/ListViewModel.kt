package com.example.androiddevchallenge.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.model.Pet

/**
 * @auther zhangduo on 2021/3/2
 */
class ListViewModel : ViewModel() {

    private val _pets = MutableLiveData<List<Pet>>()
    val pets = _pets as LiveData<List<Pet>>

    fun loadPets() {
        
    }
}
package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.model.Pet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @auther zhangduo on 2021/3/2
 */
class PetsRepo {

    companion object {
        private val petStore = mutableListOf<Pet>()
    }

    suspend fun getList(): List<Pet> = withContext(Dispatchers.IO) {
        if (petStore.isEmpty()) {

        }
        petStore.toList()
    }

    suspend fun get(id: Int): Pet? = withContext(Dispatchers.IO) {
        petStore.find { it.id == id }
    }
}
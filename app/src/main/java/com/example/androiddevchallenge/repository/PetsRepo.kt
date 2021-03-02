package com.example.androiddevchallenge.repository

import android.content.Context
import android.util.Log
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.utils.JsonTool
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.FileReader

/**
 * @auther zhangduo on 2021/3/2
 */
class PetsRepo(context: Context) {

    private val appContext = context.applicationContext

    companion object {
        private val petStore = mutableListOf<Pet>()
    }

    suspend fun getList(): List<Pet> = withContext(Dispatchers.IO) {
        if (petStore.isEmpty()) {
            petStore.addAll(readPets())
        }
        petStore.toList()
    }

    suspend fun get(id: Int): Pet? = withContext(Dispatchers.IO) {
        getList().find { it.id == id }
    }

    private fun readPets(): List<Pet> {
        val token = object : TypeToken<List<Pet>>() {}.type
        return JsonTool.gson.fromJson(
            JsonReader(appContext.assets.openFd("pets.json").createInputStream().reader()),
            token
        )
    }
}
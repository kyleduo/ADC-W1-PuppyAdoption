/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.repository

import android.content.Context
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.utils.JsonTool
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

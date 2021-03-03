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
package com.example.androiddevchallenge.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.AssetsImage

/**
 * @auther kyleduo on 2021/3/3
 */
@Composable
fun PetList(pets: LiveData<List<Pet>>, onItemClick: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Row { Text(text = "Puppy Adoption") } },
                elevation = 8.dp
            )
        }
    ) {
        Column {
            val petsList = pets.observeAsState()
            petsList.value?.let {
                LazyColumn {
                    this.items(it) { pet ->
                        PetItem(pet = pet, onItemClick)
                    }
                }
            }
        }
    }
}

@Composable
fun PetItem(pet: Pet, onItemClick: (Int) -> Unit) {
    Row(
        Modifier
            .padding(16.dp, 4.dp, 16.dp, 4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(pet.id)
            }
            .indication(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true)
            )
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = Dp(4f)
        ) {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(Dp(6f)),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                ) {
                    AssetsImage(
                        "images/${pet.photos[0]}.jpg",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        contentScale = ContentScale.Crop,
                        "photo"
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Dp(8f), end = Dp(8f))
                ) {
                    Text(text = pet.name, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(Dp(16f)))
                    Text(text = pet.breed)
                    Text(text = pet.sex)
                }
            }
        }
    }
}

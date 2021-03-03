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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.AssetsImage

/**
 * @auther kyleduo on 2021/3/3
 */
@Composable
fun PetDetail(pet: LiveData<Pet?>, onBackClick: () -> Unit) {

    val petDetail = pet.observeAsState()
    petDetail.value?.let {
        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "back",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .clickable(onClick = onBackClick)
                            .width(40.dp)
                            .height(40.dp)
                    )
                    Spacer(Modifier.width(16.dp))
                    Row {
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            color = Color.White,
                            text = it.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                    }
                }
            }
            PetDetailContent(pet = it)
        }
    }
}

@Composable
fun PetDetailContent(pet: Pet) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            Modifier
                .padding(16.dp)
        ) {

            PropertyRow(name = "Sex", value = pet.sex)
            PropertyRow(name = "Age", value = pet.age)
            PropertyRow(name = "Breed", value = pet.breed)
            PropertyRow(name = "Size", value = pet.size)
            PropertyRow(name = "Address", value = pet.location.desc)
            PropertyRow(name = "description", value = pet.description.trim())

            Spacer(modifier = Modifier.width(44.dp))

            pet.photos.forEach {
                ImageSegment(name = it, desc = pet.name)
            }
        }
    }
}

@Composable
fun PropertyRow(name: String, value: String) {
    Row(Modifier.padding(top = 8.dp, bottom = 8.dp)) {
        Text(
            text = name,
            fontSize = 14.sp,
            color = Color(0xFFAAAAAA),
            modifier = Modifier.widthIn(40.dp, 100.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color(0xFF444444),
            lineHeight = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ImageSegment(name: String, desc: String) {
    Row {
        AssetsImage(
            "images/$name.jpg",
            Modifier.requiredHeight(200.dp),
            contentScale = ContentScale.FillHeight,
            desc
        )
    }
}

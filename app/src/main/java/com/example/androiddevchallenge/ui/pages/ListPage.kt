package com.example.androiddevchallenge.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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


@Composable
fun PetList(pets: LiveData<List<Pet>>, onItemClick: (Int) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Row { Text(text = "Puppy Adoption") } },
            elevation = 8.dp
        )
    }) {
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

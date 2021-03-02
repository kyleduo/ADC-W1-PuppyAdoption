package com.example.androiddevchallenge.ui.pages

import android.graphics.BitmapFactory
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.AssetsImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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
        AssetsImage("images/$name.jpg", Modifier.requiredHeight(200.dp), contentScale = ContentScale.FillHeight, desc)
    }
}
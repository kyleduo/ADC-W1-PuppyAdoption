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
package com.example.androiddevchallenge.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.example.androiddevchallenge.utils.ImageCaches
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @auther kyleduo on 2021/3/3
 */
@Composable
fun AssetsImage(
    imagePath: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Inside,
    desc: String = "img"
) {
    var image by remember { mutableStateOf<ImageBitmap?>(null) }

    val cached = ImageCaches.get(imagePath)
    if (cached != null) {
        image = cached
    } else {
        val context = LocalContext.current
        GlobalScope.launch(Dispatchers.IO) {
            val config = BitmapFactory.Options().apply {
                inPreferredConfig = Bitmap.Config.RGB_565
            }
            val bitmap = BitmapFactory.decodeStream(context.assets.open(imagePath), null, config)
            val imageBitmap = bitmap?.asImageBitmap()
            imageBitmap?.let {
                ImageCaches.save(imagePath, it)
                launch(Dispatchers.Main) {
                    image = it
                }
            }
        }
    }

    if (image != null) {
        Image(
            painter = BitmapPainter(image!!),
            contentDescription = desc,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}

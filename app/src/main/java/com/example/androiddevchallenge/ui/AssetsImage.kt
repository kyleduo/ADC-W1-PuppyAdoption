package com.example.androiddevchallenge.ui

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
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
 * @auther zhangduo on 2021/3/3
 */
@Composable
fun AssetsImage(
    imagePath: String, modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Inside,
    desc: String = "img"
) {
    var image by remember { mutableStateOf<ImageBitmap?>(null) }

    val context = LocalContext.current
    GlobalScope.launch(Dispatchers.IO) {
        val cached = ImageCaches.get(imagePath)
        val bitmap = cached ?: BitmapFactory.decodeStream(context.assets.open(imagePath))
        launch(Dispatchers.Main) {
            image = bitmap.asImageBitmap()
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
package com.example.androiddevchallenge.utils

import android.graphics.Bitmap

/**
 * @auther zhangduo on 2021/3/3
 */
object ImageCaches {

    private val caches = mutableMapOf<String, Bitmap>()


    fun save(key: String, bitmap: Bitmap) {
        caches[key] = bitmap
    }

    fun get(key: String): Bitmap? {
        return caches[key]
    }
}
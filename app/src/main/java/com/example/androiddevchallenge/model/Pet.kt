package com.example.androiddevchallenge.model

import com.google.gson.annotations.SerializedName

/**
 * @author zhangduo on 2021/3/1
 */
data class Pet(
    val id: Int,
    val name: String,
    @SerializedName("breeds_label")
    val breed: String,
    val age: String,
    val sex: String,
    val size: String,
    val description: String,
    val photos: List<String>,
    val location: Location,
)


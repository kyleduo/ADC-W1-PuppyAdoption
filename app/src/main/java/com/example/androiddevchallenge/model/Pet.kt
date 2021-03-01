package com.example.androiddevchallenge.model

/**
 * @author zhangduo on 2021/3/1
 */
data class Pet(
    val id: Int,
    val name: String,
    val breed: String,
    val age: String,
    val sex: String,
    val size: String,
    val coatLength: String,
    val description: String,
    val photo: String,
    val location: Location,
    val contact: Contact
)


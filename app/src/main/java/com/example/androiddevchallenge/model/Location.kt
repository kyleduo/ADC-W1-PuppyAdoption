package com.example.androiddevchallenge.model

/**
 * @author zhangduo on 2021/3/1
 */
data class Location(
    val address: Address
)

data class Address(
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String
)

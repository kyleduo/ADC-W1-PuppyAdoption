package com.example.androiddevchallenge.model

/**
 * @author zhangduo on 2021/3/1
 */
data class Location(
    val address: Address
) {
    val desc: String
        get() {
            return "${address.city} ${address.state} ${address.country}"
        }
}

data class Address(
    val city: String?,
    val state: String?,
    val country: String?
)

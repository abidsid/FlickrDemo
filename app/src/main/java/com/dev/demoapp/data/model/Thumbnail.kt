package com.dev.demoapp.data.model

data class Thumbnail(
    val link: String,
    val title: String,
    val items : List<Items>
)

data class Items(
    val title : String,
    val link :String,
    val date_taken : String,
    val description : String,

)
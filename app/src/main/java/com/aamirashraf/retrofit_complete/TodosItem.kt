package com.aamirashraf.retrofit_complete

data class TodosItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)
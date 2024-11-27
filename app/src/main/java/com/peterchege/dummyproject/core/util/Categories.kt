package com.peterchege.dummyproject.core.util

import androidx.compose.ui.graphics.Color

data class Category(
    val id: Int,
    val categoryName: String,
    val color:Color
)


val categories = listOf<Category>(
    Category(
        id = 1,
        categoryName = "Food & Beverages",
        color = Color.Red
    ),
    Category(
        id = 2,
        categoryName = "Transport  & Fuel ",
        color = Color.Green
    ),
    Category(
        id = 3,
        categoryName = "Clothing",
        color = Color.Blue
    ),

)
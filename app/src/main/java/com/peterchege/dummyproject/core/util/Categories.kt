package com.peterchege.dummyproject.core.util

data class Category(
    val id: Int,
    val categoryName: String
)


val categories = listOf<Category>(
    Category(
        id = 1,
        categoryName = "Food & Beverages"
    ),
    Category(
        id = 2,
        categoryName = "Transport  & Fuel "
    ),
    Category(
        id = 3,
        categoryName = "Clothing"
    ),

)
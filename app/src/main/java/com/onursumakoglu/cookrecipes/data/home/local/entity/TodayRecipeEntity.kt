package com.onursumakoglu.cookrecipes.data.home.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodayRecipe")
data class TodayRecipeEntity (
    @PrimaryKey val day: String,
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "readyInMinutes") val readyInMinutes: Int?,
    @ColumnInfo(name = "spoonacularScore") val spoonacularScore: Int?,
    @ColumnInfo(name = "summary") val summary: String?,
    @ColumnInfo(name = "servings") val servings: Int?
)
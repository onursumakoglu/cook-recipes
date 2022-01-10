package com.onursumakoglu.cookrecipes.data.home.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodayRecipeDao {
    @Query("SELECT *  FROM TodayRecipe WHERE day = :day LIMIT 1")
    fun get(day: String): TodayRecipeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: TodayRecipeEntity)
}
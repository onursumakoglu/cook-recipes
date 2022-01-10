package com.onursumakoglu.cookrecipes.data.home.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodayRecipeEntity::class], version = 1)
abstract class HomeDatabase: RoomDatabase() {
    abstract fun todayRecipeDao(): TodayRecipeDao
}
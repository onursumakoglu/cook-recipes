package com.onursumakoglu.cookrecipes.domain

sealed class BaseResult <out T : Any, out U : Any> {
    data class Success <T: Any>(val data: T) : BaseResult<T, Nothing>()
}
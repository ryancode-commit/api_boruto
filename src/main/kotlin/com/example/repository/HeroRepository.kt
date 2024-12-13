package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Hero

interface HeroRepository {
    suspend fun getPaginationHeroes(page : Int): ApiResponse
    suspend fun searchHeroes(name: String?): ApiResponse
}
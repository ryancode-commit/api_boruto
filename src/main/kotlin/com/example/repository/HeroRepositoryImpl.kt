package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Heroes
import com.example.models.HeroesServices
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

const val NEXT_PAGE_KEY = "nextPage"
const val PREVIOUS_PAGE_KEY = "prevPage"

class HeroRepositoryImpl : HeroRepository {

    val heroesServices = HeroesServices()

    override suspend fun getPaginationHeroes(page: Int): ApiResponse {
        val pageSize = 5
        val heroes = heroesServices.getPaginatedHeroes(page, pageSize)

        val totalHeroes = transaction {
            Heroes.selectAll().count()
        }

        return ApiResponse(
            success = true,
            message = "Fetched heroes successfully",
            prevPage = calculatePage(page, totalHeroes, pageSize)[PREVIOUS_PAGE_KEY],
            nextPage = calculatePage(page, totalHeroes, pageSize)[NEXT_PAGE_KEY],
            heroes = heroes
        )
    }

    override suspend fun searchHeroes(name: String?): ApiResponse {
        val foundHeroes = heroesServices.getHeroesByName(name ?: "")

        return ApiResponse(
            success = true,
            message = "Search completed successfully",
            heroes = foundHeroes
        )
    }

    private fun calculatePage(page: Int, totalItems: Long, pageSize: Int): Map<String, Int?> {
        val totalPages = (totalItems + pageSize - 1) / pageSize // Calculate total pages
        val prevPage = if (page > 1) page - 1 else null
        val nextPage = if (page < totalPages) page + 1 else null

        return mapOf(PREVIOUS_PAGE_KEY to prevPage, NEXT_PAGE_KEY to nextPage)
    }
}
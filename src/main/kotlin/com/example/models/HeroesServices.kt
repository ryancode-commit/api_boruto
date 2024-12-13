package com.example.models

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.transaction

class HeroesServices {
    fun getAllHeroes(): List<Hero> {
        return transaction {
            Heroes.selectAll()
                .map { row ->
                Hero(
                    id = row[Heroes.id].value,
                    name = row[Heroes.name],
                    image = row[Heroes.image],
                    about = row[Heroes.about],
                    rating = row[Heroes.rating].toDouble(),
                    power = row[Heroes.power],
                    month = row[Heroes.month],
                    day = row[Heroes.day],
                    family = row[Heroes.family],
                    abilities = row[Heroes.abilities],
                    natureTypes = row[Heroes.natureTypes]
                )
            }
        }
    }

    fun getPaginatedHeroes(page: Int, pageSize: Int): List<Hero> {
        return transaction {
            Heroes.selectAll()
                .limit(n = pageSize, offset = ((page - 1) * pageSize).toLong())
                .map {
                    rowToHero(it)
                }
        }
    }

    fun getHeroesByName(name: String):List<Hero>? {
        return transaction {
            val heroes =Heroes.select { Heroes.name.lowerCase() like "%${name.lowercase()}%" }
                .map { rowToHero(it) }
            if (heroes.isEmpty()) null else heroes
        }
    }

    private fun rowToHero(row: ResultRow): Hero {
        return Hero(
            id = row[Heroes.id].value,
            name = row[Heroes.name],
            image = row[Heroes.image],
            about = row[Heroes.about],
            rating = row[Heroes.rating].toDouble(),
            power = row[Heroes.power],
            month = row[Heroes.month],
            day = row[Heroes.day],
            family = row[Heroes.family],
            abilities = row[Heroes.abilities],
            natureTypes = row[Heroes.natureTypes]
        )
    }
}
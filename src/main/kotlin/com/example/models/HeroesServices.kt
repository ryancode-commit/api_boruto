package com.example.models

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class HeroesServices {
    fun getAllHeroes(): List<Hero> {
        return transaction {
            Heroes.selectAll().map { row ->
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


//    fun addCustomer(name: String, email: String, phone: String): Int {
//        return transaction {
//            Heroes.insertAndGetId {
//                it[Customers.name] = name
//                it[Customers.email] = email
//                it[Customers.phone] = phone
//            }.value
//        }
//    }

    fun getHeroesById(id: Int): Map<String, Any>? {
        return transaction {
            Heroes.select { Heroes.id eq id }
                .map {
                    mapOf(
                        "id" to it[Heroes.id].value,
                        "name" to it[Heroes.name],
                        "image" to it[Heroes.image],
                        "about" to it[Heroes.about],
                        "rating" to it[Heroes.rating],
                        "power" to it[Heroes.power],
                        "month" to it[Heroes.month],
                        "day" to it[Heroes.day],
                        "family" to it[Heroes.family],
                        "abilities" to it[Heroes.abilities],
                        "nature_types" to it[Heroes.natureTypes]
                    )
                }
                .singleOrNull()
        }
    }
}
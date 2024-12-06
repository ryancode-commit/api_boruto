package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.vendors.currentDialect
import org.postgresql.jdbc.PgArray

@Serializable
data class Hero (
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
)

object Heroes : IntIdTable() {
    val name = varchar("name", 100)
    val image = varchar("image", 255)
    val about = text("about")
    val rating = decimal("rating", precision = 3, scale = 2)
    val power = integer("power")
    val month = varchar("month", 20)
    val day = varchar("day", 10)
    val family = registerColumn<List<String>>("family", ArrayColumnType(String::class))
    val abilities = registerColumn<List<String>>("abilities", ArrayColumnType(String::class))
    val natureTypes = registerColumn<List<String>>("nature_types", ArrayColumnType(String::class))
}

fun Table.textArray(name: String): Column<List<String>> {
    return registerColumn(name, ArrayColumnType(String::class))
}

class ArrayColumnType<T : Any>(private val type: kotlin.reflect.KClass<T>) : org.jetbrains.exposed.sql.ColumnType() {
    override fun sqlType(): String = "${currentDialect.dataTypeProvider.textType()}[]"

    override fun valueFromDB(value: Any): List<String> {
        println("DEBUG: valueFromDB received: $value (type: ${value::class})") // Debug tipe data

        return when (value) {
            is PgArray -> { // PostgreSQL mengembalikan PgArray
                println("DEBUG: Value is PgArray")
                (value.array as Array<*>).filterIsInstance<String>()
            }
            is Array<*> -> { // Jika array langsung
                println("DEBUG: Value is Array<*>")
                value.filterIsInstance<String>()
            }
            is String -> { // Jika data dalam bentuk string
                println("DEBUG: Value is String")
                parsePostgresArray(value)
            }
            else -> {
                throw IllegalStateException("Unexpected value for TEXT[]: $value (type: ${value::class})")
            }
        }
    }



    override fun notNullValueToDB(value: Any): String {
        println("DEBUG: notNullValueToDB received: $value") // Log untuk debugging
        return when (value) {
            is List<*> -> value.joinToString(",", prefix = "{", postfix = "}") { it.toString() }
            else -> throw IllegalStateException("Unexpected value type for TEXT[]: $value")
        }
    }

    private fun parsePostgresArray(array: String): List<String> {
        println("DEBUG: Parsing array: $array") // Log array sebelum parsing
        val parsed = array
            .removeSurrounding("{", "}")
            .split(",")
            .map { it.trim() }
        println("DEBUG: Parsed result: $parsed") // Log hasil setelah parsing
        return parsed
    }

}

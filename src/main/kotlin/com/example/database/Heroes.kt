package com.example.database

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.math.BigDecimal

object Heroes: IntIdTable() {
    val name = varchar("name", 100)
    val image = varchar("image", 255)
    val about = text("about")
    val rating : Column<BigDecimal> = decimal("rating", 3, 2)
    val power = integer("power")
    val month = varchar("month", 20)
    val day = varchar("day", 10)
    val family = text("family")
    val abilities = text("abilities")
    val natureTypes = text("nature_types")
}
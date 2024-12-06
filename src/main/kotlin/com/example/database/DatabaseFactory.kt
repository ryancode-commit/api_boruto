package com.example.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {
    fun init() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://localhost:5432/skripsi"
            driverClassName = "org.postgresql.Driver"
            username = "ryanardyansyah"
            password = ""
            maximumPoolSize = 10
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
        println("Database connected!")
    }
}

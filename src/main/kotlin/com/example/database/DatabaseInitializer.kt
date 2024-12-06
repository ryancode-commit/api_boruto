package com.example.database

import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
object DatabaseInitializer {
    fun init() {
        try {
            val flyway = Flyway.configure()
                .dataSource(
                    "jdbc:postgresql://localhost:5432/skripsi",
                    "ryanardyansyah",
                    ""
                )
                .baselineOnMigrate(false) // Tidak membuat baseline
                .validateOnMigrate(false) // Tidak memvalidasi migrasi
                .load()

            println("Database connected successfully without migration!")
        } catch (e: Exception) {
            println("Database connection failed: ${e.message}")
        }
    }
}

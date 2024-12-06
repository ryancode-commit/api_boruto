package com.example

import com.example.database.DatabaseFactory
import com.example.database.DatabaseInitializer
import com.example.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureKoin()
    configureDefaultHeader()
    configureSerialization()
    configureMonitoring()
    configureRouting()
    configureStatusPages()

    DatabaseInitializer.init()
    DatabaseFactory.init()
}


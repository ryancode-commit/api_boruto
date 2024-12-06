package com.example.routes

import com.example.database.Heroes
import com.example.models.ApiResponse
import com.example.models.HeroesServices
import com.example.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject

fun Routing.getAllHeroes(){

    val heroRepository : HeroRepository by inject()
    val heroesServices = HeroesServices()


    get("/boruto/heroes"){
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1

            require(page in 1..5)

            val apiResponse = heroRepository.getAllHeroes(page)

            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK)
        }catch (e: NumberFormatException){
            call.respond(
                message = ApiResponse(success = false,
                    message = "Only Numbers Allowed."),
                status = HttpStatusCode.BadRequest
            )
        }catch (e: IllegalArgumentException){
            call.respond(
                message = ApiResponse(success = false,
                    message = "Heroes not Found."),
                status = HttpStatusCode.NotFound
            )
        }
    }

    route("/heroes") {
        get {
            try {
                val heroes = heroesServices.getAllHeroes()
                call.respond(heroes) // Kirim data ke client
            } catch (e: Exception) {
                call.respondText(
                    "Failed to fetch heroes: ${e.localizedMessage}",
                    status = HttpStatusCode.InternalServerError
                )
            }
        }
    }


//    get("/users") {
//        transaction {
//            // Mengambil data dari database
//            val users = Heroes.select { Heroes.name greaterEq 0 }.map {
//                mapOf(
//                    "name" to it[Heroes.name]
//                )
//            }
//            call.respond(
//                message = ApiResponse(success = false,
//                    message = "Heroes not Found."),x
//                status = HttpStatusCode.NotFound
//            )
//        }
//    }
}
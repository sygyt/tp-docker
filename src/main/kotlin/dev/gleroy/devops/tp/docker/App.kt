package dev.gleroy.devops.tp.docker

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.sql.DriverManager

data class StatusDto(
    val status: String
)

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, module = Application::module).start(true)
}

fun Application.module() {
    val logger = LoggerFactory.getLogger(Application::class.java)

    install(ContentNegotiation) {
        jackson()
    }

    routing {
        get {
            val dbUrl = System.getProperty("database.url")
            val dbUsername = System.getProperty("database.username")
            val dbPassword = System.getProperty("database.password")
            try {
                DriverManager.getConnection("jdbc:postgresql://$dbUrl", dbUsername, dbPassword)
                call.respond(StatusDto("up"))
            } catch (exception: Exception) {
                logger.error("Unable to connect to database", exception)
                call.respond(StatusDto("down"))
            }
        }
    }
}

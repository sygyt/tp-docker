package dev.gleroy.devops.tp.docker

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotlintest.matchers.types.shouldNotBeNull
import io.kotlintest.shouldBe
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import org.junit.jupiter.api.Test
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication

class AppTest {
    val mapper = ObjectMapper().findAndRegisterModules()

    @Test
    fun up() = withTestApplication(Application::module) {
        with(handleRequest(HttpMethod.Get, "/")) {
            response.status() shouldBe HttpStatusCode.OK
            response.content.shouldNotBeNull()
            mapper.readValue<StatusDto>(response.content!!) shouldBe StatusDto("up")
        }
    }
}

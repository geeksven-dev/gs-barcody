package com.geeksven

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class PingPongResourceTest {

    @Test
    fun testPingPongEndpoint() {
        given()
          .`when`().get("/ping")
          .then()
             .statusCode(200)
             .body(`is`("pong pong"))
    }

}
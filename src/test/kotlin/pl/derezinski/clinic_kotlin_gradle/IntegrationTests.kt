package pl.derezinski.clinic_kotlin_gradle

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun assertClinicPageTitleAndContentAndStatusCode() {
        println(">> Assert Clinic page title, content and status code")
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h3 style=\"color: darkgreen; font-weight: bold\">Health First</h3>")
    }

    @Test
    fun assertCreatePatientPageContentAndStatusCode() {
        println(">> Assert createPatient page content and status code")
        val entity = restTemplate.getForEntity<String>("/create-patient")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<p style=\"font-weight: bold\">Creating a new patient</p>")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }

}
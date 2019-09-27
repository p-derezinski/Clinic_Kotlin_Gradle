package pl.derezinski.clinic_kotlin_gradle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClinicKotlinGradleApplication

fun main(args: Array<String>) {
    runApplication<ClinicKotlinGradleApplication>(*args)
}

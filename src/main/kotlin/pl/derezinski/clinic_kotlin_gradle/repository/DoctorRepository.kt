package pl.derezinski.clinic_kotlin_gradle.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.derezinski.clinic_kotlin_gradle.model.Doctor

@Repository
interface DoctorRepository : JpaRepository<Doctor, Long> {

    fun findFirstById(id: Long): Doctor

}
package pl.derezinski.clinic_kotlin_gradle.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.derezinski.clinic_kotlin_gradle.model.Patient

@Repository
interface PatientRepository : JpaRepository<Patient, Long> {

    fun findFirstById(id: Long): Patient

}
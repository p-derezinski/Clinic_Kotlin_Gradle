package pl.derezinski.clinic_kotlin_gradle.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.derezinski.clinic_kotlin_gradle.model.Appointment
import pl.derezinski.clinic_kotlin_gradle.model.Patient

@Repository
interface AppointmentRepository : JpaRepository<Appointment, Long> {

    fun findFirstById(id: Long): Appointment

    fun findAllByPatient(patient: Patient): List<Appointment>

}
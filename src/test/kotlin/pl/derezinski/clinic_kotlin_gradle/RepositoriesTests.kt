package pl.derezinski.clinic_kotlin_gradle

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import pl.derezinski.clinic_kotlin_gradle.model.*
import pl.derezinski.clinic_kotlin_gradle.repository.AppointmentRepository
import pl.derezinski.clinic_kotlin_gradle.repository.DoctorRepository
import pl.derezinski.clinic_kotlin_gradle.repository.PatientRepository
import java.time.LocalDate

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTests @Autowired constructor(val entityManager: TestEntityManager,
                                               val patientRepository: PatientRepository,
                                               val doctorRepository: DoctorRepository,
                                               val appointmentRepository: AppointmentRepository) {

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun when_findFirstById_thenReturnPatient() {
        val janKowalski = Patient("Jan", "Kowalski", "Jasna 12", "62-000", "Warszawa")
        entityManager.persist(janKowalski)
        entityManager.flush()
        val patient = patientRepository.findFirstById(janKowalski.id)
        assertThat(patient).isEqualTo(janKowalski)
    }

    @Test
    fun when_findFirstById_thenReturnDoctor() {
        val tomaszJudym = Doctor("Tomasz", "Judym", MedicalSpecialization.SURGEON)
        entityManager.persist(tomaszJudym)
        entityManager.flush()
        val doctor = doctorRepository.findFirstById(tomaszJudym.id)
        assertThat(doctor).isEqualTo(tomaszJudym)
    }

    @Test
    fun when_findFirstById_thenReturnAppointment() {
        val janKowalski = Patient("Jan", "Kowalski", "Jasna 12", "62-000", "Warszawa")
        entityManager.persist(janKowalski)
        val tomaszJudym = Doctor("Tomasz", "Judym", MedicalSpecialization.SURGEON)
        entityManager.persist(tomaszJudym)
        val appointmentJanAndTomasz = Appointment(LocalDate.now(), "12:00", Location.HEALTH_FIRST_POZNAN_CENTER, tomaszJudym, janKowalski)
        entityManager.persist(appointmentJanAndTomasz)
        entityManager.flush()
        val appointment = appointmentRepository.findFirstById(appointmentJanAndTomasz.id)
        assertThat(appointment).isEqualTo(appointmentJanAndTomasz)
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }

}
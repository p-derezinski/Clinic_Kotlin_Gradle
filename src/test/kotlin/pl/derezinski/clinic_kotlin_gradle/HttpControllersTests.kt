package pl.derezinski.clinic_kotlin_gradle

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import pl.derezinski.clinic_kotlin_gradle.model.*
import pl.derezinski.clinic_kotlin_gradle.service.AppointmentService
import pl.derezinski.clinic_kotlin_gradle.service.DoctorService
import pl.derezinski.clinic_kotlin_gradle.service.PatientService
import java.time.LocalDate

@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var patientService: PatientService

    @MockkBean
    lateinit var doctorService: DoctorService

    @MockkBean
    lateinit var appointmentService: AppointmentService

    @Test
    fun listOnePatient() {
        val janKowalski = Patient("Jan", "Kowalski", "Jasna 12", "62-000", "Warszawa")
        every { patientService.getFirstById(555) } returns janKowalski
        mockMvc.perform(get("/api/patient/555").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.firstName").value(janKowalski.firstName))
                .andExpect(jsonPath("\$.street").value(janKowalski.street))
    }

    @Test
    fun listAllPatients() {
        val janKowalski = Patient("Jan", "Kowalski", "Jasna 12", "62-000", "Warszawa")
        val michalNowak = Patient("Michał", "Nowak", "Szkolna 21", "63-111", "Kraków")
        every { patientService.all } returns listOf(janKowalski, michalNowak)
        mockMvc.perform(get("/api/patient/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.[0].firstName").value(janKowalski.firstName))
                .andExpect(jsonPath("\$.[1].firstName").value(michalNowak.firstName))
                .andExpect(jsonPath("\$.[0].city").value(janKowalski.city))
    }

    @Test
    fun listOneDoctor() {
        val tomaszJudym = Doctor("Tomasz", "Judym", MedicalSpecialization.SURGEON)
        every { doctorService.getFirstById(555) } returns tomaszJudym
        mockMvc.perform(get("/api/doctor/555").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.firstName").value(tomaszJudym.firstName))
    }

    @Test
    fun listAllAppointments() {
        val janKowalski = Patient("Jan", "Kowalski", "Jasna 12", "62-000", "Warszawa")
        val michalNowak = Patient("Michał", "Nowak", "Szkolna 21", "63-111", "Kraków")
        val tomaszJudym = Doctor("Tomasz", "Judym", MedicalSpecialization.SURGEON)
        val appointmentJanAndTomasz = Appointment(LocalDate.now(), "12:00", Location.HEALTH_FIRST_POZNAN_CENTER, tomaszJudym, janKowalski)
        val appointmentMichalAndTomasz = Appointment(LocalDate.now(), "14:00", Location.HEALTH_FIRST_POZNAN_RATAJE, tomaszJudym, michalNowak)
        every { appointmentService.all } returns listOf(appointmentJanAndTomasz, appointmentMichalAndTomasz)
        mockMvc.perform(get("/api/appointment/appointments/patient").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.[0].patient.firstName").value(janKowalski.firstName))
                .andExpect(jsonPath("\$.[0].appointmentDate").value(appointmentJanAndTomasz.appointmentDate.toString()))
                .andExpect(jsonPath("\$.[1].patient.lastName").value(michalNowak.lastName))
                .andExpect(jsonPath("\$.[1].appointmentTime").value(appointmentMichalAndTomasz.appointmentTime))
    }

}
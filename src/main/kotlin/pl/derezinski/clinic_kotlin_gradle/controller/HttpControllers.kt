package pl.derezinski.clinic_kotlin_gradle.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pl.derezinski.clinic_kotlin_gradle.controller.dto.AppointmentDto
import pl.derezinski.clinic_kotlin_gradle.controller.dto.DoctorDto
import pl.derezinski.clinic_kotlin_gradle.controller.dto.PatientDto
import pl.derezinski.clinic_kotlin_gradle.model.Location
import pl.derezinski.clinic_kotlin_gradle.service.AppointmentService
import pl.derezinski.clinic_kotlin_gradle.service.DoctorService
import pl.derezinski.clinic_kotlin_gradle.service.PatientService
import java.time.LocalDate

@RestController
@RequestMapping("/api/patient")
class HttpPatientController @Autowired constructor(internal var patientService: PatientService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) = patientService.getFirstById(id)

    @GetMapping("/all")
    fun findAll() = patientService.all

    @PostMapping("/")
    fun addPatient(@RequestBody patientDto: PatientDto) = patientService.savePatient(patientDto)

    @PutMapping("/{id}")
    fun updatePatient(@PathVariable id: Long, @RequestBody patientDto: PatientDto) {
        val patientToUpdate = patientService.getFirstById(id)
        patientToUpdate.firstName = patientDto.firstName ?: patientToUpdate.firstName
        patientToUpdate.lastName = patientDto.lastName ?: patientToUpdate.lastName
        patientToUpdate.street = patientDto.street ?: patientToUpdate.street
        patientToUpdate.zipCode = patientDto.zipCode ?: patientToUpdate.zipCode
        patientToUpdate.city = patientDto.city ?: patientToUpdate.city
        patientService.update(patientToUpdate)
    }

    @DeleteMapping("/{id}")
    fun deletePatient(@PathVariable id: Long) = patientService.deleteById(id)

}

@RestController
@RequestMapping("/api/doctor")
class HttpDoctorController @Autowired constructor(internal var doctorService: DoctorService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) = doctorService.getFirstById(id)

    @PostMapping("/")
    fun addDoctor(@RequestBody doctorDto: DoctorDto) = doctorService.saveDoctor(doctorDto)

    @PutMapping("/{id}")
    fun updateDoctor(@PathVariable id: Long, @RequestBody doctorDto: DoctorDto) {
        val doctorToUpdate = doctorService.getFirstById(id)
        doctorToUpdate.firstName = doctorDto.firstName ?: doctorToUpdate.firstName
        doctorToUpdate.lastName = doctorDto.lastName ?: doctorToUpdate.lastName
        doctorToUpdate.medicalSpecialization = doctorDto.medicalSpecialization ?: doctorToUpdate.medicalSpecialization
        doctorService.update(doctorToUpdate)
    }

    @DeleteMapping("/{id}")
    fun deleteDoctor(@PathVariable id: Long) = doctorService.deleteById(id)

}

@RestController
@RequestMapping("/api/appointment")
class HttpAppointmentController @Autowired constructor(internal var appointmentService: AppointmentService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) = appointmentService.getFirstById(id)

    @GetMapping("/appointments/patient")
    fun findAll() = appointmentService.all

    @GetMapping("/appointments/patient/{id}")
    fun findAllByPatient(@PathVariable id: Long) = appointmentService.getAllByPatient(id)

    @PostMapping("/")
    fun addAppointment(@RequestBody jsonHolder: Map<String, String>) {
        val appointmentDto = AppointmentDto()
        appointmentDto.appointmentDate = LocalDate.parse(jsonHolder["appointmentDate"])
        appointmentDto.appointmentTime = jsonHolder["appointmentTime"]
        appointmentDto.location = Location.valueOf(jsonHolder["location"]!!)
        appointmentDto.doctorId = jsonHolder["doctorId"]!!.toLong()
        val patientId = jsonHolder["patientId"]!!.toLong()
        appointmentService.saveAppointment(appointmentDto, patientId)
    }

    @PutMapping("/{id}")
    fun updateAppointment(@PathVariable id: Long, @RequestBody jsonHolder: Map<String, String>) {
        val appointmentToUpdate = appointmentService.getFirstById(id)
        appointmentToUpdate.appointmentTime = jsonHolder["newAppointmentTime"] ?: appointmentToUpdate.appointmentTime
        appointmentService.update(appointmentToUpdate)
    }

    @DeleteMapping("/{id}")
    fun deleteAppointment(@PathVariable id: Long) = appointmentService.deleteById(id)

}
package pl.derezinski.clinic_kotlin_gradle.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pl.derezinski.clinic_kotlin_gradle.controller.dto.PatientDto
import pl.derezinski.clinic_kotlin_gradle.service.PatientService

@RestController
@RequestMapping("/api/patient")
class HttpPatientController @Autowired constructor(internal var patientService: PatientService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) = patientService.getFirstById(id)

    @GetMapping("/all")
    fun findAll() = patientService.all

    @PostMapping("/")
    fun addPatient(@RequestBody patientDto: PatientDto) {
        patientService.savePatient(patientDto)
    }

//    @PutMapping("/{id}")
//    fun updatePatient(@PathVariable id: Long, @RequestBody patientDto: PatientDto) {
//        patientService.update(patientService.getFirstById(id))
//    }

    @DeleteMapping("/{id}")
    fun deletePatient(@PathVariable id: Long) {
        patientService.deleteById(id)
    }

}
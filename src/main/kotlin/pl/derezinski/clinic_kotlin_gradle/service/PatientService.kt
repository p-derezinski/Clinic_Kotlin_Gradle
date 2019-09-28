package pl.derezinski.clinic_kotlin_gradle.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.derezinski.clinic_kotlin_gradle.controller.dto.PatientDto
import pl.derezinski.clinic_kotlin_gradle.model.Patient
import pl.derezinski.clinic_kotlin_gradle.repository.PatientRepository

@Service
class PatientService @Autowired
constructor(internal var patientRepository: PatientRepository) {

    val all: List<Patient>
        get() = patientRepository.findAll()

    fun savePatient(patientDto: PatientDto) {
        val patient = Patient(patientDto.firstName!!, patientDto.lastName!!,
                patientDto.street!!, patientDto.zipCode!!, patientDto.city!!)
        patientRepository.save(patient)
    }

    fun getFirstById(id: Long): Patient {
        return patientRepository.findFirstById(id)
    }

    fun update(patient: Patient) {
        patientRepository.save(patient)
    }

    fun deleteById(id: Long) {
        patientRepository.deleteById(id)
    }
}
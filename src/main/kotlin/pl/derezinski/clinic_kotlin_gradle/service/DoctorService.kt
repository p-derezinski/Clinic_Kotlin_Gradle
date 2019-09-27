package pl.derezinski.clinic_kotlin_gradle.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.derezinski.clinic_kotlin_gradle.controller.dto.DoctorDto
import pl.derezinski.clinic_kotlin_gradle.model.Doctor
import pl.derezinski.clinic_kotlin_gradle.repository.DoctorRepository
import java.util.ArrayList

@Service
class DoctorService @Autowired
constructor(internal var doctorRepository: DoctorRepository) {

    val all: List<Doctor>
        get() = doctorRepository.findAll()

    val allIdNumbers: List<Long>
        get() {
            val listOfDoctors = all
            val listOfIdNumbers = ArrayList<Long>()
            for (doctor in listOfDoctors) {
                listOfIdNumbers.add(doctor.id)
            }
            return listOfIdNumbers
        }

    fun saveDoctor(doctorDto: DoctorDto) {
        val doctor = Doctor(doctorDto.firstName!!, doctorDto.lastName!!, doctorDto.medicalSpecialization!!)
        doctorRepository.save(doctor)
    }

    fun getFirstById(id: Long): Doctor {
        return doctorRepository.findFirstById(id)
    }

    fun update(doctor: Doctor) {
        doctorRepository.save(doctor)
    }

    fun deleteById(id: Long) {
        doctorRepository.deleteById(id)
    }
}
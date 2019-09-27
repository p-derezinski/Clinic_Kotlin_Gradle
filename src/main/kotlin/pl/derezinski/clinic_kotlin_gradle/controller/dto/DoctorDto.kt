package pl.derezinski.clinic_kotlin_gradle.controller.dto

import lombok.Data
import pl.derezinski.clinic_kotlin_gradle.model.MedicalSpecialization

@Data
class DoctorDto {

    var firstName: String? = null
    var lastName: String? = null
    var medicalSpecialization: MedicalSpecialization? = null

}
package pl.derezinski.clinic_kotlin_gradle.controller.dto

import lombok.Data

@Data
class PatientDto {

    var firstName: String? = null
    var lastName: String? = null
    var street: String? = null
    var zipCode: String? = null
    var city: String? = null

}
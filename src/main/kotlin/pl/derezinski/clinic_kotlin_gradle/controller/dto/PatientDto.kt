package pl.derezinski.clinic_kotlin_gradle.controller.dto

import lombok.Data

@Data
class PatientDto {

    var firstName: String? = null
        set(firstName) {
            field = this.firstName
        }
    var lastName: String? = null
        set(lastName) {
            field = this.lastName
        }
    var street: String? = null
        set(street) {
            field = this.street
        }
    var zipCode: String? = null
        set(zipCode) {
            field = this.zipCode
        }
    var city: String? = null
        set(city) {
            field = this.city
        }

}
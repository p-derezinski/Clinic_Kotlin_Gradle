package pl.derezinski.clinic_kotlin_gradle.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var firstName: String
    var lastName: String
    var medicalSpecialization: MedicalSpecialization

    constructor(firstName: String, lastName: String, medicalSpecialization: MedicalSpecialization) {
        this.firstName = firstName
        this.lastName = lastName
        this.medicalSpecialization = medicalSpecialization
    }
}
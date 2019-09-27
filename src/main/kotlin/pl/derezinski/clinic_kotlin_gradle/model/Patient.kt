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
class Patient {

    @Id                                               // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.AUTO)   // AUTO_INCREMENT
    var id: Long = 0
    var firstName: String
    var lastName: String
    var street: String
    var zipCode: String
    var city: String

    constructor(firstName: String, lastName: String, street: String, zipCode: String, city: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.street = street
        this.zipCode = zipCode
        this.city = city
    }
}
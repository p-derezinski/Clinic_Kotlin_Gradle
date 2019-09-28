package pl.derezinski.clinic_kotlin_gradle.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.time.LocalDate
import javax.persistence.*

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var appointmentDate: LocalDate
    var appointmentTime: String
    var location: Location

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "doctorId")
    var doctor: Doctor

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "patientId")
    var patient: Patient

    constructor(appointmentDate: LocalDate, appointmentTime: String, location: Location, doctor: Doctor, patient: Patient) {
        this.appointmentDate = appointmentDate
        this.appointmentTime = appointmentTime
        this.location = location
        this.doctor = doctor
        this.patient = patient
    }
}
package pl.derezinski.clinic_kotlin_gradle.controller.dto

import lombok.Data
import org.springframework.format.annotation.DateTimeFormat
import pl.derezinski.clinic_kotlin_gradle.model.Location
import java.time.LocalDate

@Data
class AppointmentDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var appointmentDate: LocalDate? = null

    var appointmentTime: String? = null
    var location: Location? = null
    var doctorId: Long? = null

}
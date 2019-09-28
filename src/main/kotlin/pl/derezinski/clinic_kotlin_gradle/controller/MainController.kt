package pl.derezinski.clinic_kotlin_gradle.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import pl.derezinski.clinic_kotlin_gradle.service.AppointmentService
import pl.derezinski.clinic_kotlin_gradle.service.DoctorService
import pl.derezinski.clinic_kotlin_gradle.service.PatientService

@Controller
class MainController @Autowired
constructor(internal var patientService: PatientService,
            internal var doctorService: DoctorService,
            internal var appointmentService: AppointmentService) {

    @GetMapping("/")
    fun showHomePage(model: Model): String {
        model.addAttribute("clinicName", "Health First")
        val listOfPatients = patientService.all
        val listOfDoctors = doctorService.all
        val listOfAppointments = appointmentService.allForExistingPatientsAndDoctors
        model.addAttribute("listOfPatients", listOfPatients)
        model.addAttribute("listOfDoctors", listOfDoctors)
        model.addAttribute("listOfAppointments", listOfAppointments)
        return "index"
    }

}

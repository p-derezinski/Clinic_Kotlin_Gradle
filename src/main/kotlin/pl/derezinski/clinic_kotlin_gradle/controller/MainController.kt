package pl.derezinski.clinic_kotlin_gradle.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import pl.derezinski.clinic_kotlin_gradle.service.PatientService

@Controller
class MainController @Autowired
constructor(internal var patientService: PatientService) {

    @GetMapping("/")
    fun showHomePage(model: Model): String {
        model.addAttribute("clinicName", "Health First")
        val listOfPatients = patientService.all
        model.addAttribute("listOfPatients", listOfPatients)
        return "index"
    }

}

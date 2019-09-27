package pl.derezinski.clinic_kotlin_gradle.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import pl.derezinski.clinic_kotlin_gradle.controller.dto.PatientDto
import pl.derezinski.clinic_kotlin_gradle.model.Patient
import pl.derezinski.clinic_kotlin_gradle.service.PatientService
import javax.validation.Valid

@Controller
class PatientController @Autowired
constructor(internal var patientService: PatientService) {

    // entering the page that allows to create a new patient
    @GetMapping("/create-patient")
    fun createPatient(model: Model): String {
        model.addAttribute("patient", PatientDto())
        return "createPatient"
    }

    // handling the creating of a new patient
    @PostMapping("/patient")
    fun createPatient(@ModelAttribute("patient") @Valid patientDto: PatientDto,
                      bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "createPatient"
        }
        patientService.savePatient(patientDto)
        return "redirect:/"
    }

    // handling the displaying of a patient
    @GetMapping("/patient/{id}")
    fun showPatient(@PathVariable("id") id: Long, model: Model): String {
        val patientToView = patientService.getFirstById(id)
        model.addAttribute("patientToView", patientToView)
        return "patient"
    }

    // handling the updating of patient data
    @PutMapping("/patient/{id}")
    fun updatePatient(@PathVariable("id") id: Long, @ModelAttribute("patientToView") @Valid patient: Patient): String {
        patientService.update(patient)
        return "redirect:/patient/{id}"
    }

    // handling the deleting of a patient
    @DeleteMapping("/patient/{id}")
    fun deletePatient(@PathVariable("id") id: Long): String {
        patientService.deleteById(id)
        return "redirect:/"
    }
}
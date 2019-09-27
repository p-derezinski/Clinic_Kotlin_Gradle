package pl.derezinski.clinic_kotlin_gradle.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import pl.derezinski.clinic_kotlin_gradle.controller.dto.DoctorDto
import pl.derezinski.clinic_kotlin_gradle.model.Doctor
import pl.derezinski.clinic_kotlin_gradle.service.DoctorService
import javax.validation.Valid

@Controller
class DoctorController @Autowired
constructor(internal var doctorService: DoctorService) {

    // entering the page that allows to create a new doctor
    @GetMapping("/create-doctor")
    fun createDoctor(model: Model): String {
        model.addAttribute("doctor", DoctorDto())
        return "createDoctor"
    }

    // handling the creating of a new doctor
    @PostMapping("/doctor")
    fun createDoctor(@ModelAttribute("doctor") @Valid doctorDto: DoctorDto,
                     bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "createDoctor"
        }
        doctorService.saveDoctor(doctorDto)
        return "redirect:/"
    }

    // handling the displaying of a doctor
    @GetMapping("/doctor/{id}")
    fun showDoctor(@PathVariable("id") id: Long, model: Model): String {
        val doctorToView = doctorService.getFirstById(id)
        model.addAttribute("doctorToView", doctorToView)
        return "doctor"
    }

    // handling the updating of doctor data
    @PutMapping("/doctor/{id}")
    fun updateDoctor(@PathVariable("id") id: Long, @ModelAttribute("doctorToView") @Valid doctor: Doctor): String {
        doctorService.update(doctor)
        return "redirect:/doctor/{id}"
    }

    // handling the deleting of a doctor
    @DeleteMapping("/doctor/{id}")
    fun deleteDoctor(@PathVariable("id") id: Long): String {
        doctorService.deleteById(id)
        return "redirect:/"
    }
}
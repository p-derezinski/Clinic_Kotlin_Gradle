package pl.derezinski.clinic_kotlin_gradle.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import pl.derezinski.clinic_kotlin_gradle.controller.dto.AppointmentDto
import pl.derezinski.clinic_kotlin_gradle.service.AppointmentService
import pl.derezinski.clinic_kotlin_gradle.service.DoctorService
import pl.derezinski.clinic_kotlin_gradle.service.PatientService
import javax.validation.Valid

@Controller
class AppointmentController @Autowired
constructor(internal var appointmentService: AppointmentService,
            internal var patientService: PatientService,
            internal var doctorService: DoctorService) {

    // entering the page that allows to make an appointment
    @GetMapping("/make-appointment/{id}")
    fun makeAppointment(@PathVariable("id") id: Long, model: Model): String {
        return addAttributesToTheModelAndReturnMakeAppointment(id, model)
    }

    // handling the making of a new appointment
    @PostMapping("/appointment")
    fun makeAppointment(@ModelAttribute("appointment") @Valid appointmentDto: AppointmentDto,
                        @RequestParam(name = "patientId") patientId: Long,
                        model: Model,
                        bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "makeAppointment"
        }
        // handling the case when an ID of a non-existing doctor was entered in the form
        if (!doctorService.allIdNumbers.contains(appointmentDto.doctorId)) {
            val errorMessage = "There is no doctor with the chosen ID. Please insert data again."
            model.addAttribute("errorMessage", errorMessage)
            return addAttributesToTheModelAndReturnMakeAppointment(patientId, model)
        }
        appointmentService.saveAppointment(appointmentDto, patientId)
        return "redirect:/"
    }

    // handling the displaying of an appointment
    @GetMapping("/appointment/{id}")
    fun showAppointment(@PathVariable("id") id: Long, model: Model): String {
        val appointmentToView = appointmentService.getFirstById(id)
        model.addAttribute("appointmentToView", appointmentToView)
        return "appointment"
    }

    // handling the updating of appointment data
    @PutMapping("/appointment/{id}")
    fun updateAppointment(@PathVariable("id") id: Long, @RequestParam(name = "newAppointmentTime") newAppointmentTime: String): String {
        val appointmentToUpdate = appointmentService.getFirstById(id)
        appointmentToUpdate.appointmentTime = newAppointmentTime
        appointmentService.update(appointmentToUpdate)
        return "redirect:/appointment/{id}"
    }

    // handling the deleting of an appointment
    @DeleteMapping("/appointment/{id}")
    fun deleteAppointment(@PathVariable("id") id: Long): String {
        appointmentService.deleteById(id)
        return "redirect:/"
    }

    // handling the displaying of all appointments
    @GetMapping("/appointments/patient")
    fun showAllAppointments(model: Model): String {
        val listOfAppointments = appointmentService.all
        model.addAttribute("listOfAppointments", listOfAppointments)
        return "appointmentsAll"
    }

    // handling the displaying of appointments of a selected patient
    @GetMapping("/appointments/patient/{id}")
    fun showAppointmentsByPatient(@PathVariable("id") patientId: Long, model: Model): String {
        val listOfAppointments = appointmentService.getAllByPatient(patientId)
        model.addAttribute("listOfAppointments", listOfAppointments)
        val patientToView = patientService.getFirstById(patientId)
        model.addAttribute("patientToView", patientToView)
        return "appointmentsByPatient"
    }

    private fun addAttributesToTheModelAndReturnMakeAppointment(id: Long, model: Model): String {
        model.addAttribute("appointment", AppointmentDto())
        val patientToView = patientService.getFirstById(id)
        model.addAttribute("patientToView", patientToView)
        val listOfDoctors = doctorService.all
        model.addAttribute("listOfDoctors", listOfDoctors)
        return "makeAppointment"
    }
}
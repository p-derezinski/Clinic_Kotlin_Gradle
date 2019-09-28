package pl.derezinski.clinic_kotlin_gradle.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.derezinski.clinic_kotlin_gradle.controller.dto.AppointmentDto
import pl.derezinski.clinic_kotlin_gradle.model.Appointment
import pl.derezinski.clinic_kotlin_gradle.repository.AppointmentRepository
import pl.derezinski.clinic_kotlin_gradle.repository.DoctorRepository
import pl.derezinski.clinic_kotlin_gradle.repository.PatientRepository

@Service
class AppointmentService @Autowired
constructor(internal var appointmentRepository: AppointmentRepository,
            internal var patientRepository: PatientRepository,
            internal var doctorRepository: DoctorRepository) {

    val all: List<Appointment>
        get() = appointmentRepository.findAll()

    val allForExistingPatientsAndDoctors: List<Appointment>
        get() {
            val listOfAppointments = appointmentRepository.findAll()
            var listOfAppointmentsToModify = appointmentRepository.findAll()
            for (appointment in listOfAppointments) {
                if (appointment.patient == null || appointment.doctor == null) {
                    listOfAppointmentsToModify.remove(appointment)
                }
            }
            return listOfAppointmentsToModify
        }

    fun saveAppointment(appointmentDto: AppointmentDto, patientId: Long) {
        val patient = patientRepository.findFirstById(patientId)
        val doctor = doctorRepository.findFirstById(appointmentDto.doctorId!!)
        val appointment = Appointment(appointmentDto.appointmentDate!!, appointmentDto.appointmentTime!!,
                appointmentDto.location!!, doctor, patient)
        appointmentRepository.save(appointment)
    }

    fun getFirstById(id: Long): Appointment {
        return appointmentRepository.findFirstById(id)
    }

    fun getAllByPatient(patientId: Long): List<Appointment> {
        val patient = patientRepository.findFirstById(patientId)
        return appointmentRepository.findAllByPatient(patient)
    }

    fun update(appointment: Appointment) {
        appointmentRepository.save(appointment)
    }

    fun deleteById(id: Long) {
        appointmentRepository.deleteById(id)
    }
}
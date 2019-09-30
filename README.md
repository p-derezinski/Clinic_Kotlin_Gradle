# Clinic_Kotlin_Gradle


1. Description

1.1. Name of the Application

Clinic_Kotlin_Gradle

1.2. Purpose of the Application

The purpose of this simple application is to help administer the clinic.  



2. Overview

The application is created using the following technologies:
- Kotlin
- Spring Boot
- Gradle
- Remote MySQL
- HTML and Thymeleaf
- JUnit 5 and SpringMockK



3. Data design

- Patients are created using the form and added to the database. A patient is characterized by ID, first name, last name and address (street, ZIP code and city).
- Doctors are created using the form and added to the database. A doctor is characterized by ID, first name, last name and medical specialization.
- Appointments are created using the appointment making form and added to the database. An appointment is characterized by ID, appointment date, appointment time and location. It also contains the references to the patient and to the doctor.  



4. Functionality

4.1. Home page

Home page presents the name of the clinic, the list of patients, the list of doctors and the list of appointments. It also contains buttons that allow to navigate to the form for creating a new patient, the form for creating a new doctor, the list of all appointments (including invalid appointments - with deleted patient or doctor) and the section for each individual patient, doctor and appointment. There are also buttons that allow to delete the selected patient, doctor or appointment.

4.2. Patient's section

This section presents personal data of the selected patient and allows to update personal data. It also contains buttons that allow to navigate to the form for making a new appointment and the list of all appointments of that patient.

4.3. Doctor's section

This section presents personal data of the selected doctor and allows to update personal data.

4.4. Appointment details

This section presents details of the selected appointment and allows to update the appointment time.

4.5. Forms for creating a new patient and doctor

These sections allow to create a new patient or doctor by providing all necessary information.

4.6. Form for making a new appointment

This section allows to make a new appointment for the specific patient by providing all necessary information, including the ID of the doctor. In case there is no doctor with the provided ID, the adequate information is displayed. In order to make it easier to provide a valid ID, the list of doctors with their ID's is also presented.

4.7. Exposing HTTP API

HTTP API is also implemented via @RestController annotated controllers.

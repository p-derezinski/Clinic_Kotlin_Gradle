package pl.derezinski.clinic_kotlin_gradle.controller

import org.springframework.stereotype.Controller
import sun.security.x509.OIDMap.addAttribute
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping("/")
    fun showHomePage(model: Model): String {
        model.addAttribute("clinicName", "Health First")
        return "index"
    }

}

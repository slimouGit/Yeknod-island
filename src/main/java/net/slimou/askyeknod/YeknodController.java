package net.slimou.askyeknod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class YeknodController {

    private YeknodService yeknodService;

    public YeknodController(YeknodService yeknodService) {
        this.yeknodService = yeknodService;
    }

    @GetMapping(path = "/home")
    public String homeOfTheYeknod(){
        return "index";
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("text", ""); // Initialisieren des Textfelds mit leerem Inhalt
        return "index";
    }

    @PostMapping
    public String processForm(String text, Model model) {
        model.addAttribute("text", text); // Das eingegebene Textfeld wird im Model gespeichert
        String response = this.yeknodService.startRequest(text);
        model.addAttribute("response", response); // Die Response des Service wird im Model gespeichert

        return "index";
    }
}

package net.slimou.askyeknod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}

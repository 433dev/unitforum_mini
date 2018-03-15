package ftt.unitforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
    @RequestMapping("/")
    public String actioneIndex(Model model) {
        return "error/404";
    }
}

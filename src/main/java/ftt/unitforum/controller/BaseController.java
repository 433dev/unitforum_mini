package ftt.unitforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;


public class BaseController {
    @Autowired
    protected ApplicationContext context;
    
    @ModelAttribute
    private void setCommonModelAttribute(Model model) {
    }
}

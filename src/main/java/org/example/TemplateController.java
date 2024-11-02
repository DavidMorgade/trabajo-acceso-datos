package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {
    @GetMapping("/template")
    String template() {
        return "simple-template";
    }


    @GetMapping("variables")
    String variables(ModelMap model) {
        model.put("variable", "Hello, World!");
        return "variable-template";
    }
}

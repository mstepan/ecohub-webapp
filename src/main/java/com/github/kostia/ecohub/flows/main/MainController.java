package com.github.kostia.ecohub.flows.main;

import com.github.kostia.ecohub.config.EcohubConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    private final EcohubConfig ecohubConfig;

    public MainController(EcohubConfig ecohubConfig) {
        this.ecohubConfig = ecohubConfig;
    }

    @GetMapping("/")
    public String main(HttpServletRequest request, Model model) {

//        HttpSession session = request.getSession(true);
        model.addAttribute("idToken", "my-cool-idtoken");

        return "index";
    }

}

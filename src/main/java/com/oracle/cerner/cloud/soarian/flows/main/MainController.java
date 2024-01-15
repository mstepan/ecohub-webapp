package com.oracle.cerner.cloud.soarian.flows.main;

import com.oracle.cerner.cloud.soarian.config.IdcsConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    private final IdcsConfig idcsConfig;

    public MainController(IdcsConfig idcsConfig) {
        this.idcsConfig = idcsConfig;
    }

    @GetMapping("/")
    public String main(HttpServletRequest request, Model model) {

//        HttpSession session = request.getSession(true);

        model.addAttribute("idToken", "my-cool-idtoken");

        return "index";
    }

}

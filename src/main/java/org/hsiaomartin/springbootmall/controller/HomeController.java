package org.hsiaomartin.springbootmall.controller;

import org.hsiaomartin.springbootmall.dto.UserLoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userLogin")
public class HomeController {

    // 首頁
    @GetMapping("/")
    public String home(Model model) {

        return "home";
    }
}

package org.hsiaomartin.springbootmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    // 首頁
    @GetMapping("/")
    public String home() {

        return "home";
    }

    // 註冊頁面
    @GetMapping("/registerPage")
    public String registerPage() {

        return "register";
    }

    // 登入頁面
    @GetMapping("/loginPage")
    public String loginPage() {

        return "login";
    }
}

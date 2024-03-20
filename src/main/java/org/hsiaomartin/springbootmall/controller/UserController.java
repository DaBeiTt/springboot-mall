package org.hsiaomartin.springbootmall.controller;

import jakarta.validation.Valid;
import org.hsiaomartin.springbootmall.util.SuccessObject;
import org.hsiaomartin.springbootmall.dto.UserLoginRequest;
import org.hsiaomartin.springbootmall.dto.UserRegisterRequest;
import org.hsiaomartin.springbootmall.model.User;
import org.hsiaomartin.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("userLogin")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public String register(@Valid UserRegisterRequest userRegisterRequest,
                                   Model model) {

        userService.register(userRegisterRequest);

        SuccessObject successObject = new SuccessObject("register", "註冊成功！");
        model.addAttribute("success", successObject);

        return "message/success";
    }

    @PostMapping("/users/login")
    public String login(@Valid UserLoginRequest userLoginRequest,
                        Model model) {

        User user = userService.login(userLoginRequest);

        model.addAttribute("userLogin", user);

        return "redirect:/products";
    }

    // 登出
    @GetMapping("users/logout")
    public String logout(SessionStatus sessionStatus) {

        // 設定為完成會話，清除所有 session
        sessionStatus.setComplete();

        return "redirect:/";
    }

    // 註冊頁面
    @GetMapping("/registerPage")
    public String registerPage(Model model) {

        model.addAttribute("userRegisterRequest", new UserRegisterRequest());

        return "member/register";
    }

    // 登入頁面
    @GetMapping("/loginPage")
    public String loginPage(Model model) {

        model.addAttribute("userLoginRequest", new UserLoginRequest());

        return "member/login";
    }
}

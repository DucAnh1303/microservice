package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        if (session != null && session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            return "redirect:/home"; // Nếu đã đăng nhập, chuyển hướng đến trang home
        }
        return "login"; // Nếu chưa đăng nhập, hiển thị trang login
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // Trả về trang home.html khi đăng nhập thành công
    }

    @GetMapping("/logout-success")
    public String logoutSuccess(HttpSession session) {
        return "redirect:/login";
    }

}

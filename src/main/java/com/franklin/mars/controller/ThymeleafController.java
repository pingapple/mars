package com.franklin.mars.controller;


import com.franklin.mars.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("th")
public class ThymeleafController {
    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "frank is here");
        return "thymeleaf/index";
    }

    @RequestMapping("/test")
    public String test(ModelMap map) {
        User user = new User();
        user.setAge(12);
        user.setBirthday(new Date());
        user.setName("James");
        user.setPassword("121212");
        user.setDesc("<font color='green'><strong>hello</strong></font>");
        map.addAttribute("user", user);
        return "thymeleaf/test";
    }

    @RequestMapping("/error")
    public String error() {
        int a = 1 / 0;
        return "thymeleaf/error";
    }
}

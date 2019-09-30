package com.objectspace.coorperation.web.mapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestMapping {
    @RequestMapping("test01")
    public String Test01() {
        return "test01";
    }
    @RequestMapping("index")
    public String index() {
        return "index";
    }
    @RequestMapping("login")
    public String login() {
        return "login";
    }
}


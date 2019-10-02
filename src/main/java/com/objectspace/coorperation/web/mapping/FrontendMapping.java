package com.objectspace.coorperation.web.mapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend")
public class FrontendMapping {
    @RequestMapping("/login")
    public String login(){
        return "/frontend/login";
    }
    @RequestMapping("/index")
    public String index(){ return "/frontend/index";}
    @RequestMapping("/register")
    public String register(){return "/frontend/register";}
}

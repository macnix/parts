package com.javarush.parts.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author dubetskyi_ov on 16.09.2018
 */
@Controller
public class HomeController {
    @RequestMapping(value = {"","/index"}, method = RequestMethod.GET)
    public String viewHome() {
        return "/index";
    }
}

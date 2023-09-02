package com.example.intelGraphicsApi.controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ModelController {
    @RequestMapping("/")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("src/index");
        return modelAndView;
    }
    @RequestMapping("/registration")
    public ModelAndView registration () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("src/register");
        return modelAndView;
    }
    @RequestMapping("/operations")
    public ModelAndView operations () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("src/operations");
        return modelAndView;
    }

}

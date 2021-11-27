package es.jmmluna.tim.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class TIMErrorController implements ErrorController  {

    @RequestMapping("/error")
    public String handleError() {
        return "error";
//    	throw new RuntimeException("test exception");
    }
}
package com.stylefeng.guns.modular.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cluster")
public class ClusterController {

//    @RequestMapping("/hadoop")
//    @ResponseBody
//    public synchronized String index() {
//        System.out.print(new HrtDisDetModel().NaiveBayesModelFunction());
//        return "true";
//    }

    @RequestMapping("")
    public String testRedSSS(HttpServletResponse response) throws Exception {
        System.out.println("ssss");
        response.sendRedirect("http://192.168.71.130:9870");
        return "Test";
//        return "redirect:http://192.168.71.130:9870";
    }
}

package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import hdd.models.HrtDisDetModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/test")
public class MlSparkController extends BaseController {

    /**
     * 跳转到通知列表首页
     */
    @RequestMapping("")
    @ResponseBody
    public synchronized String index() {
        System.out.print(new HrtDisDetModel().NaiveBayesModelFunction());
        return "true";
    }

    @RequestMapping("/test2")
    public String testRedSSS(HttpServletResponse response) throws Exception {
        System.out.println("ssss");
        return "redirect:http://192.168.71.130:9870";
    }

    @RequestMapping("/list")
    public String modelList(HttpServletResponse response) throws Exception {

        System.out.println("ssss");
        return "redirect:http://192.168.71.130:9870";
    }
}

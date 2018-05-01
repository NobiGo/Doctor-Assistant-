package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.modular.system.service.IHeartdisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private IHeartdisService heartdisService;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("sex", generateSexData());
        return "/system/chart/ChartMgr.html";
    }

    @RequestMapping("/sex")
    @ResponseBody
    public String generateSexData() {
        List<String> data = new ArrayList<String>();
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        for (int status = 0; status <= 1; status++) {
            data.add(df.format(heartdisService.countBySex(status) / (double) heartdisService.countByStatus(1) * 100.0));
        }
        return JSON.toJSONString(data);
    }


    @RequestMapping("/cp")
    @ResponseBody
    public String generateCpData() {
        List<String> data = new ArrayList<String>();
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        for (int status = 1; status <= 4; status++) {
            data.add(df.format(heartdisService.countByCp(status) / (double) heartdisService.countByStatus(1) * 100));
        }
        return JSON.toJSONString(data);
    }


    /**
     * exang
     */
    @RequestMapping("/exang")
    @ResponseBody
    public String generateExangData() {
        List<String> data = new ArrayList<String>();
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        for (int status = 0; status <= 1; status++) {
//            data.add(df.format(heartdisService.countByExang(status)/(double)heartdisService.countByStatus(1)*100));
            data.add(df.format(heartdisService.countByExang(status)));
        }
        return JSON.toJSONString(data);
    }

    /**
     * age
     */
    @RequestMapping("/age")
    @ResponseBody
    public String generateAgeData() {
        List<Long> data = new ArrayList<Long>();
        for (int status = 30; status <= 80; status += 10) {
            data.add(heartdisService.countByAge(status - 10, status));
        }
        return JSON.toJSONString(data);
    }


    @RequestMapping("/ageLabels")
    @ResponseBody
    public String generateYearLabels() {
        List<String> list = new ArrayList<>();
        for (int i = 80; i >= 20; i = i - 10) {
            list.add(0, i + "岁");
        }
        return JSON.toJSONString(list);
    }


    @RequestMapping("/thalachLabels")
    @ResponseBody
    public String generateThalachLabels() {
        List<String> list = new ArrayList<>();
        for (int i = 210; i >= 70; i = i - 10) {
            list.add(0, i + "");
        }
        return JSON.toJSONString(list);
    }

    /**
     * 126  564
     */
    @RequestMapping("/cholLabels")
    @ResponseBody
    public String generateCholLabels() {
        List<String> list = new ArrayList<>();
        for (int i = 440; i >= 120; i = i - 20) {
            list.add(0, i + "");
        }
        return JSON.toJSONString(list);
    }
    /**
     * age
     */
    @RequestMapping("/chol")
    @ResponseBody
    public String generateCholData() {
        List<Long> data = new ArrayList<Long>();
        for (int status = 120; status <= 440; status += 20) {
            data.add(heartdisService.countByChol(status - 20, status));
        }
        return JSON.toJSONString(data);
    }






    /**
     * age
     */
    @RequestMapping("/thalach")
    @ResponseBody
    public String generateThalachData() {
        List<Long> data = new ArrayList<Long>();
        for (int status = 70; status <= 210; status += 10) {
            data.add(heartdisService.countByThalach(status - 10, status));
        }
        return JSON.toJSONString(data);
    }
}

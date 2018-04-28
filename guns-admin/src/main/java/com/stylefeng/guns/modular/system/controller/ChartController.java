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
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        for (int status = 0; status <= 1; status++) {
            data.add(df.format(heartdisService.countBySex(status)/(double)heartdisService.countByStatus(1)*100.0));
        }
        return JSON.toJSONString(data);
    }


    @RequestMapping("/cp")
    @ResponseBody
    public String generateCpData() {
        List<String> data = new ArrayList<String>();
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        for (int status = 1; status <= 4; status++) {
            data.add(df.format(heartdisService.countByCp(status)/(double)heartdisService.countByStatus(1)*100));
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
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        for (int status = 0; status <= 1; status++) {
//            data.add(df.format(heartdisService.countByExang(status)/(double)heartdisService.countByStatus(1)*100));
            data.add(df.format(heartdisService.countByExang(status)));
        }
        return JSON.toJSONString(data);
    }
}

package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.warpper.RoleWarpper;
import hdd.models.HrtDisDetModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class MlSparkController extends BaseController {

    /**
     * 跳转到通知列表首页
     */
    @RequestMapping("")
    @ResponseBody
    public synchronized String index() {
        System.out.print( new HrtDisDetModel().NaiveBayesModelFunction());
        return "true";
    }
}

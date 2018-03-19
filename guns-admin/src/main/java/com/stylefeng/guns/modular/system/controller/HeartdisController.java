package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Heartdis;
import com.stylefeng.guns.modular.system.service.IHeartdisService;

/**
 * 疾病预测控制器
 *
 * @author fengshuonan
 * @Date 2018-03-19 11:21:00
 */
@Controller
@RequestMapping("/heartdis")
public class HeartdisController extends BaseController {

    private String PREFIX = "/system/heartdis/";

    @Autowired
    private IHeartdisService heartdisService;

    /**
     * 跳转到疾病预测首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "heartdis.html";
    }

    /**
     * 跳转到添加疾病预测
     */
    @RequestMapping("/heartdis_add")
    public String heartdisAdd() {
        return PREFIX + "heartdis_add.html";
    }

    /**
     * 跳转到修改疾病预测
     */
    @RequestMapping("/heartdis_update/{heartdisId}")
    public String heartdisUpdate(@PathVariable Integer heartdisId, Model model) {
        Heartdis heartdis = heartdisService.selectById(heartdisId);
        model.addAttribute("item",heartdis);
        LogObjectHolder.me().set(heartdis);
        return PREFIX + "heartdis_edit.html";
    }

    /**
     * 获取疾病预测列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return heartdisService.selectList(null);
    }

    /**
     * 新增疾病预测
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Heartdis heartdis) {
        heartdisService.insert(heartdis);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除疾病预测
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer heartdisId) {
        heartdisService.deleteById(heartdisId);
        return SUCCESS_TIP;
    }

    /**
     * 修改疾病预测
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Heartdis heartdis) {
        heartdisService.updateById(heartdis);
        return super.SUCCESS_TIP;
    }

    /**
     * 疾病预测详情
     */
    @RequestMapping(value = "/detail/{heartdisId}")
    @ResponseBody
    public Object detail(@PathVariable("heartdisId") Integer heartdisId) {
        return heartdisService.selectById(heartdisId);
    }
}

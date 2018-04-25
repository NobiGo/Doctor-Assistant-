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
import com.stylefeng.guns.common.persistence.model.Smodel;
import com.stylefeng.guns.modular.system.service.ISmodelService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-04-25 12:18:59
 */
@Controller
@RequestMapping("/smodel")
public class SmodelController extends BaseController {

    private String PREFIX = "/system/smodel/";

    @Autowired
    private ISmodelService smodelService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "smodel.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/smodel_add")
    public String smodelAdd() {
        return PREFIX + "smodel_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/smodel_update/{smodelId}")
    public String smodelUpdate(@PathVariable Integer smodelId, Model model) {
        Smodel smodel = smodelService.selectById(smodelId);
        model.addAttribute("item",smodel);
        LogObjectHolder.me().set(smodel);
        return PREFIX + "smodel_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return smodelService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Smodel smodel) {
        smodelService.insert(smodel);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer smodelId) {
        smodelService.deleteById(smodelId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Smodel smodel) {
        smodelService.updateById(smodel);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{smodelId}")
    @ResponseBody
    public Object detail(@PathVariable("smodelId") Integer smodelId) {
        return smodelService.selectById(smodelId);
    }
}

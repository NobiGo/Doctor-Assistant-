package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.persistence.model.Heartdis;
import com.stylefeng.guns.common.persistence.model.PreHeartdis;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.service.IPreHeartdisService;
import com.stylefeng.guns.modular.system.warpper.PreHeartdisWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-03-19 16:43:57
 */
@Controller
@RequestMapping("/preHeartdis")
public class PreHeartdisController extends BaseController {

    private String PREFIX = "/system/preHeartdis/";

    @Autowired
    private IPreHeartdisService preHeartdisService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "preHeartdis.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/preHeartdis_add")
    public String preHeartdisAdd() {
        return PREFIX + "preHeartdis_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/preHeartdis_update/{preHeartdisId}")
    public String preHeartdisUpdate(@PathVariable Integer preHeartdisId, Model model) {
        PreHeartdis preHeartdis = preHeartdisService.selectById(preHeartdisId);
        model.addAttribute("item", preHeartdis);
        LogObjectHolder.me().set(preHeartdis);
        return PREFIX + "preHeartdis_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> preHeartdis = preHeartdisService.selectMaps(null);
        return super.warpObject(new PreHeartdisWarpper(preHeartdis));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PreHeartdis preHeartdis) {
        preHeartdisService.insert(preHeartdis);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer preHeartdisId) {
        preHeartdisService.deleteById(preHeartdisId);
        return SUCCESS_TIP;
    }

    /**
     * 更改状态
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public Object updateStatus(@RequestParam Integer preHeartdisId) {
        PreHeartdis preHeartdis = preHeartdisService.selectById(preHeartdisId);
        preHeartdis.setCa(preHeartdis.getCa()==0?1:0);
        preHeartdisService.updateById(preHeartdis);
        return SUCCESS_TIP;
    }



//    /**
//     * 修改
//     */
//    @RequestMapping(value = "/update")
//    @ResponseBody
//    public Object update(PreHeartdis preHeartdis) {
//        preHeartdisService.updateById(preHeartdis);
//        return super.SUCCESS_TIP;
//    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{preHeartdisId}")
    @ResponseBody
    public Object detail(@PathVariable("preHeartdisId") Integer preHeartdisId) {
        return preHeartdisService.selectById(preHeartdisId);
    }
}

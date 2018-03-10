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
import com.stylefeng.guns.common.persistence.model.Patient;
import com.stylefeng.guns.modular.system.service.IPatientService;

/**
 * 患者数据控制器
 *
 * @author fengshuonan
 * @Date 2018-03-10 10:46:32
 */
@Controller
@RequestMapping("/patient")
public class PatientController extends BaseController {

    private String PREFIX = "/system/patient/";

    @Autowired
    private IPatientService patientService;

    /**
     * 跳转到患者数据首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "patient.html";
    }

    /**
     * 跳转到添加患者数据
     */
    @RequestMapping("/patient_add")
    public String patientAdd() {
        return PREFIX + "patient_add.html";
    }

    /**
     * 跳转到修改患者数据
     */
    @RequestMapping("/patient_update/{patientId}")
    public String patientUpdate(@PathVariable Integer patientId, Model model) {
        Patient patient = patientService.selectById(patientId);
        model.addAttribute("item",patient);
        LogObjectHolder.me().set(patient);
        return PREFIX + "patient_edit.html";
    }

    /**
     * 获取患者数据列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return patientService.selectList(null);
    }

    /**
     * 新增患者数据
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Patient patient) {
        patientService.insert(patient);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除患者数据
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer patientId) {
        patientService.deleteById(patientId);
        return SUCCESS_TIP;
    }

    /**
     * 修改患者数据
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Patient patient) {
        patientService.updateById(patient);
        return super.SUCCESS_TIP;
    }

    /**
     * 患者数据详情
     */
    @RequestMapping(value = "/detail/{patientId}")
    @ResponseBody
    public Object detail(@PathVariable("patientId") Integer patientId) {
        return patientService.selectById(patientId);
    }
}

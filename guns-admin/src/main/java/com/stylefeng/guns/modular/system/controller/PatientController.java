package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.persistence.model.Patient;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.util.DateFormatUtil;
import com.stylefeng.guns.modular.system.service.IPatientService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


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
    public String patientUpdate(@PathVariable Integer patientId, Model model) throws Exception {
        Patient patient = patientService.selectById(patientId);
        String updateTime = DateFormatUtil.changeDate(patient.getUpdateTime().toString());
        String addTime = DateFormatUtil.changeDate(patient.getAddTime().toString());
        Map<String, String> value = null;
        try {
            value = BeanUtils.describe(patient);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        value.put("updateTime", updateTime);
        value.put("addTime", addTime);
        model.addAttribute("item", value);
        LogObjectHolder.me().set(value);
        return PREFIX + "patient_edit.html";
    }

    /**
     * 获取患者数据列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", condition);
        return patientService.selectByMap(map);
    }

    /**
     * 新增患者数据
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Patient patient) {
        patient.setUpdateTime(new Date());
        patient.setAddTime(new Date());
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
        patient.setUpdateTime(new Date());
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

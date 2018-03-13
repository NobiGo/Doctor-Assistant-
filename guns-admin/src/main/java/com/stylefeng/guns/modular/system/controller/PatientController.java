package com.stylefeng.guns.modular.system.controller;

import com.alibaba.druid.util.StringUtils;
import com.stylefeng.guns.common.persistence.model.Patient;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.util.DateFormatUtil;
import com.stylefeng.guns.modular.system.service.IPatientService;
import com.zte.datamask.name.ChinaNameMask;
import com.zte.datamask.number.IDNumberMask;
import com.zte.datamask.number.PhoneNumberMask;
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
import java.util.List;
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
    public Object list(@RequestParam(required = false) String condition, @RequestParam(required = false) String NameMaskType, @RequestParam(required = false) String TelMaskType, @RequestParam(required = false) String IdCardMaskType) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(condition))
            map.put("name", condition);
        List<Patient> value = patientService.selectByMap(map);
        Map<String, String> mask = new HashMap<String, String>();
        if (!StringUtils.isEmpty(NameMaskType))
            mask.put("NameMaskType", NameMaskType);
        if (!StringUtils.isEmpty(TelMaskType))
            mask.put("TelMaskType", TelMaskType);
        if (!StringUtils.isEmpty(IdCardMaskType))
            mask.put("IdCardMaskType", IdCardMaskType);
        return mask(value, mask);
    }

//    /**
//     * 获取脱敏数据
//     */
//    @RequestMapping(value = "/mask")
//    @ResponseBody
//    public Object mask(@RequestParam(required = false) String condition, @RequestParam(required = false) String NameMaskType) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        if (!StringUtils.isEmpty(condition))
//            map.put("name", condition);
//        List<Patient> value = patientService.selectByMap(map);
//        if (NameMaskType.equals("1")) {
//            for (int i = 0; i < value.size(); i++) {
//                value.get(i).setName("雄");
//            }
//        }
//        return value;
//    }
//

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


    //数据脱敏
    private List<Patient> mask(List<Patient> value, Map<String, String> para) {
        if (para.containsKey("NameMaskType")) {
            String NameMaskType = para.get("NameMaskType");
            switch (NameMaskType) {
                //数据隐藏
                case "1":
                    for (Patient patient : value) {
                        patient.setName(ChinaNameMask.masking_china_name_hiding(patient.getName(), 2, 3, '*'));
                    }
                    break;
                //数据全名hash算法
                case "2":
                    for (Patient patient : value) {
                        patient.setName(ChinaNameMask.masking_china_name_full_hash(patient.getName(), "dx"));
                    }
                    break;
//                //数据脱敏-名
//                case "3":
//                    for (Patient patient : value) {
//                        patient.setName(ChinaNameMask.masking_china_name_last_hash(patient.getName(), "dx"));
//                    }
//                    break;
//                //数据脱敏姓
//                case "4":
//                    for (Patient patient : value) {
//                        patient.setName(ChinaNameMask.masking_china_name_first_hash(patient.getName(), "dx"));
//                    }
//                    break;
            }
        }
        if (para.containsKey("TelMaskType")) {
            String TelMaskType = para.get("TelMaskType");
            switch (TelMaskType) {
                //乱码—保留网络号
                case "1":
                    for (Patient patient : value) {
                        patient.setTel(PhoneNumberMask.masking_phone_number_randomandreplace(patient.getTel(), 1, '#', true));
                    }
                    break;
                //乱码—保留地区编码
                case "2":
                    for (Patient patient : value) {
                        patient.setTel(PhoneNumberMask.masking_phone_number_randomandreplace(patient.getTel(), 2, '#', true));
                    }
                    break;
                //乱码—保留网络号+地区编码
                case "3":
                    for (Patient patient : value) {
                        patient.setTel(PhoneNumberMask.masking_phone_number_randomandreplace(patient.getTel(), 3, '#', true));
                    }
                    break;
                //隐藏—保留网络号
                case "4":
                    for (Patient patient : value) {
                        patient.setTel(PhoneNumberMask.masking_phone_number_randomandreplace(patient.getTel(), 1, '#', false));
                    }
                    break;
                //隐藏—保留地区编码
                case "5":
                    for (Patient patient : value) {
                        patient.setTel(PhoneNumberMask.masking_phone_number_randomandreplace(patient.getTel(), 2, '#', false));
                    }
                    break;
                //隐藏—保留网络号+地区编码
                case "6":
                    for (Patient patient : value) {
                        patient.setTel(PhoneNumberMask.masking_phone_number_randomandreplace(patient.getTel(), 3, '#', false));
                    }
            }
        }
        if (para.containsKey("IdCardMaskType")) {
            try {
                for (Patient patient : value) {
                    patient.setIdCard(IDNumberMask.masking_chinese_idnum_hash(patient.getIdCard()));
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }


        String AddressMaskType = para.get("AddressMaskType");
        String BankMaskType = para.get("BankMaskType");
        return value;
    }
}

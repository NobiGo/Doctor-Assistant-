package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-19
 */
public class Heartdis extends Model<Heartdis> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("patient_id")
    private Integer patientId;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 胸部疼痛类型
     */
    private Integer cp;
    /**
     *  静息血压
     */
    private Double trestbpss;
    /**
     * 以mg/dl为单位的血清类固醇
     */
    private Double chol;
    /**
     * 空腹血糖（空腹血糖 > 120 mg/dl) (1 = 是; 0 = 否)）
     */
    private Integer fbs;
    /**
     * （0,1,2）
     */
    private Integer restecg;
    /**
     * 达到的最大心率
     */
    private Double thalach;
    /**
     * 是否运动诱发的心绞痛 (1 = 是; 0 = 否)
     */
    private Integer exang;
    /**
     * 由与相对休息有明显差异的运动诱导的ST段压低
     */
    private Double oldpeak;
    private Integer slope;
    /**
     * 被透视荧光检查（flourosopy）标注颜色的大血管的数量 (0-3)
     */
    private Integer ca;
    private Double thal;
    /**
     * 心脏病的诊断 (冠状动脉疾病状态)
     */
    private Integer num;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public Double getTrestbpss() {
        return trestbpss;
    }

    public void setTrestbpss(Double trestbpss) {
        this.trestbpss = trestbpss;
    }

    public Double getChol() {
        return chol;
    }

    public void setChol(Double chol) {
        this.chol = chol;
    }

    public Integer getFbs() {
        return fbs;
    }

    public void setFbs(Integer fbs) {
        this.fbs = fbs;
    }

    public Integer getRestecg() {
        return restecg;
    }

    public void setRestecg(Integer restecg) {
        this.restecg = restecg;
    }

    public Double getThalach() {
        return thalach;
    }

    public void setThalach(Double thalach) {
        this.thalach = thalach;
    }

    public Integer getExang() {
        return exang;
    }

    public void setExang(Integer exang) {
        this.exang = exang;
    }

    public Double getOldpeak() {
        return oldpeak;
    }

    public void setOldpeak(Double oldpeak) {
        this.oldpeak = oldpeak;
    }

    public Integer getSlope() {
        return slope;
    }

    public void setSlope(Integer slope) {
        this.slope = slope;
    }

    public Integer getCa() {
        return ca;
    }

    public void setCa(Integer ca) {
        this.ca = ca;
    }

    public Double getThal() {
        return thal;
    }

    public void setThal(Double thal) {
        this.thal = thal;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Heartdis{" +
        "id=" + id +
        ", patientId=" + patientId +
        ", age=" + age +
        ", sex=" + sex +
        ", cp=" + cp +
        ", trestbpss=" + trestbpss +
        ", chol=" + chol +
        ", fbs=" + fbs +
        ", restecg=" + restecg +
        ", thalach=" + thalach +
        ", exang=" + exang +
        ", oldpeak=" + oldpeak +
        ", slope=" + slope +
        ", ca=" + ca +
        ", thal=" + thal +
        ", num=" + num +
        "}";
    }
}

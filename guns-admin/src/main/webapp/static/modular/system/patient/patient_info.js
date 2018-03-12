/**
 * 初始化患者数据详情对话框
 */
var PatientInfoDlg = {
    patientInfoData: {}
};

/**
 * 清除数据
 */
PatientInfoDlg.clearData = function () {
    this.patientInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PatientInfoDlg.set = function (key, val) {
    this.patientInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PatientInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PatientInfoDlg.close = function () {
    parent.layer.close(window.parent.Patient.layerIndex);
}

/**
 * 收集数据
 */
PatientInfoDlg.collectData = function () {
    this
        .set('id')
        .set('name')
        .set('tel')
        .set('idCard')
        .set('address')
        .set('bankNo')
        .set('onHospital')
}

/**
 * 提交添加
 */
PatientInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/patient/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Patient.table.refresh();
        PatientInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.patientInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PatientInfoDlg.editSubmit = function () {
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/patient/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Patient.table.refresh();
        PatientInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.patientInfoData);
    ajax.start();
}

$(function () {

});

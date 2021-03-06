/**
 * 初始化详情对话框
 */
var PreHeartdisInfoDlg = {
    preHeartdisInfoData : {}
};

/**
 * 清除数据
 */
PreHeartdisInfoDlg.clearData = function() {
    this.preHeartdisInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PreHeartdisInfoDlg.set = function(key, val) {
    this.preHeartdisInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PreHeartdisInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PreHeartdisInfoDlg.close = function() {
    parent.layer.close(window.parent.PreHeartdis.layerIndex);
}

/**
 * 收集数据
 */
PreHeartdisInfoDlg.collectData = function() {
    this
    .set('id')
    .set('patientId')
    .set('age')
    .set('sex')
    .set('cp')
    .set('trestbpss')
    .set('chol')
    .set('fbs')
    .set('restecg')
    .set('thalach')
    .set('exang')
    .set('oldpeak')
    .set('slope')
    .set('ca')
    .set('thal')
    .set('num');
}

/**
 * 提交添加
 */
PreHeartdisInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/preHeartdis/add", function(data){
        Feng.success("添加成功!");
        window.parent.PreHeartdis.table.refresh();
        PreHeartdisInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.preHeartdisInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PreHeartdisInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/preHeartdis/update", function(data){
        Feng.success("修改成功!");
        window.parent.PreHeartdis.table.refresh();
        PreHeartdisInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.preHeartdisInfoData);
    ajax.start();
}

$(function() {

});

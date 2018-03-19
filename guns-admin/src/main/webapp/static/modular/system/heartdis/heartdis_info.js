/**
 * 初始化疾病预测详情对话框
 */
var HeartdisInfoDlg = {
    heartdisInfoData : {}
};

/**
 * 清除数据
 */
HeartdisInfoDlg.clearData = function() {
    this.heartdisInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HeartdisInfoDlg.set = function(key, val) {
    this.heartdisInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HeartdisInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HeartdisInfoDlg.close = function() {
    parent.layer.close(window.parent.Heartdis.layerIndex);
}

/**
 * 收集数据
 */
HeartdisInfoDlg.collectData = function() {
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
HeartdisInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/heartdis/add", function(data){
        Feng.success("添加成功!");
        window.parent.Heartdis.table.refresh();
        HeartdisInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.heartdisInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HeartdisInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/heartdis/update", function(data){
        Feng.success("修改成功!");
        window.parent.Heartdis.table.refresh();
        HeartdisInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.heartdisInfoData);
    ajax.start();
}

$(function() {

});

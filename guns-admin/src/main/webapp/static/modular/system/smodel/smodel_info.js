/**
 * 初始化详情对话框
 */
var SmodelInfoDlg = {
    smodelInfoData : {}
};

/**
 * 清除数据
 */
SmodelInfoDlg.clearData = function() {
    this.smodelInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SmodelInfoDlg.set = function(key, val) {
    this.smodelInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SmodelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SmodelInfoDlg.close = function() {
    parent.layer.close(window.parent.Smodel.layerIndex);
}

/**
 * 收集数据
 */
SmodelInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('accuracy')
    .set('test1')
    .set('test2');
}

/**
 * 提交添加
 */
SmodelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/smodel/add", function(data){
        Feng.success("添加成功!");
        window.parent.Smodel.table.refresh();
        SmodelInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.smodelInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SmodelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/smodel/update", function(data){
        Feng.success("修改成功!");
        window.parent.Smodel.table.refresh();
        SmodelInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.smodelInfoData);
    ajax.start();
}

$(function() {

});

/**
 * 初始化详情对话框
 */
var ModelInfoDlg = {
    modelInfoData : {}
};

/**
 * 清除数据
 */
ModelInfoDlg.clearData = function() {
    this.modelInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ModelInfoDlg.set = function(key, val) {
    this.modelInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ModelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ModelInfoDlg.close = function() {
    parent.layer.close(window.parent.Model.layerIndex);
}

/**
 * 收集数据
 */
ModelInfoDlg.collectData = function() {
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
ModelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/model/add", function(data){
        Feng.success("添加成功!");
        window.parent.Model.table.refresh();
        ModelInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.modelInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ModelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/model/update", function(data){
        Feng.success("修改成功!");
        window.parent.Model.table.refresh();
        ModelInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.modelInfoData);
    ajax.start();
}

$(function() {

});

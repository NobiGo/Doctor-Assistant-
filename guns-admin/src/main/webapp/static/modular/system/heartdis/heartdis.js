/**
 * 疾病预测管理初始化
 */
var Heartdis = {
    id: "HeartdisTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Heartdis.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '患者id', field: 'patientId', visible: true, align: 'center', valign: 'middle'},
        {title: '年龄', field: 'age', visible: true, align: 'center', valign: 'middle'},
        {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle'},
        {title: '胸部疼痛', field: 'cp', visible: true, align: 'center', valign: 'middle'},
        {title: ' 静息血压', field: 'trestbpss', visible: true, align: 'center', valign: 'middle'},
        {title: '血清类固醇', field: 'chol', visible: true, align: 'center', valign: 'middle'},
        {title: '空腹血糖', field: 'fbs', visible: true, align: 'center', valign: 'middle'},
        {title: '心电图结果', field: 'restecg', visible: true, align: 'center', valign: 'middle'},
        {title: '最大心率', field: 'thalach', visible: true, align: 'center', valign: 'middle'},
        {title: '是否心绞痛', field: 'exang', visible: true, align: 'center', valign: 'middle'},
        {title: 'ST段压低', field: 'oldpeak', visible: true, align: 'center', valign: 'middle'},
        {title: '心电图倾斜度', field: 'slope', visible: true, align: 'center', valign: 'middle'},
        {title: '血管数', field: 'ca', visible: true, align: 'center', valign: 'middle'},
        {title: '缺陷种类', field: 'thal', visible: true, align: 'center', valign: 'middle'},
        {title: '是否患病', field: 'num', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Heartdis.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Heartdis.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加疾病预测
 */
Heartdis.openAddHeartdis = function () {
    var index = layer.open({
        type: 2,
        title: '添加疾病预测',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/heartdis/heartdis_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看疾病预测详情
 */
Heartdis.openHeartdisDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '疾病预测详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/heartdis/heartdis_update/' + Heartdis.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除疾病预测
 */
Heartdis.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/heartdis/delete", function (data) {
            Feng.success("删除成功!");
            Heartdis.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("heartdisId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询疾病预测列表
 */
Heartdis.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Heartdis.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Heartdis.initColumn();
    var table = new BSTable(Heartdis.id, "/heartdis/list", defaultColunms);
    table.setPaginationType("client");
    Heartdis.table = table.init();
});

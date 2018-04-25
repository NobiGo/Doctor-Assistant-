/**
 * 管理初始化
 */
var Smodel = {
    id: "SmodelTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Smodel.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '模型id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '模型名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '模型准确率', field: 'accuracy', visible: true, align: 'center', valign: 'middle'}
        // {title: '', field: 'test1', visible: true, align: 'center', valign: 'middle'},
        // {title: '', field: 'test2', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Smodel.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Smodel.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Smodel.openAddSmodel = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/smodel/smodel_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Smodel.openSmodelDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/smodel/smodel_update/' + Smodel.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Smodel.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/smodel/delete", function (data) {
            Feng.success("删除成功!");
            Smodel.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("smodelId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Smodel.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Smodel.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Smodel.initColumn();
    var table = new BSTable(Smodel.id, "/smodel/list", defaultColunms);
    table.setPaginationType("client");
    Smodel.table = table.init();
});

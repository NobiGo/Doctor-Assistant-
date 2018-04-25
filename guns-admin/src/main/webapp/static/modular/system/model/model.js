/**
 * 管理初始化
 */
var Model = {
    id: "ModelTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Model.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'accuracy', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'test1', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'test2', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Model.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Model.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Model.openAddModel = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/model/model_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Model.openModelDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/model/model_update/' + Model.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Model.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/model/delete", function (data) {
            Feng.success("删除成功!");
            Model.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("modelId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Model.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Model.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Model.initColumn();
    var table = new BSTable(Model.id, "/model/list", defaultColunms);
    table.setPaginationType("client");
    Model.table = table.init();
});

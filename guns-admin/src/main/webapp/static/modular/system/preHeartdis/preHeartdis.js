/**
 * 管理初始化
 */
var PreHeartdis = {
    id: "PreHeartdisTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PreHeartdis.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
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
        {title: '缺陷种类', field: 'thal', visible: true, align: 'center', valign: 'middle'},
        {title: '心电图诊断', field: 'num', visible: true, align: 'center', valign: 'middle'},
        {title: '预测结果', field: 'ca', visible: true, align: 'center', valign: 'middle'},
        {
            title: '操作', visible: true, align: 'center', valign: 'middle', formatter: function (value, row, index) {
                // if (row.state == 3) {
                //     return '<button type="button" class="btn btn-danger button-margin" onclick="PreHeartdis.delete(' + row.id + ')" id=""><i class="fa fa-arrows-alt"></i>&nbsp;删除</button>';
                // } else {
                    return '<button type="button" class="btn btn-primary button-margin" onclick="PreHeartdis.update(' + row.id + ')" id=""><i class="fa fa-edit"></i>&nbsp;纠正诊断结果</button>' +
                        '<button type="button" class="btn btn-danger button-margin" onclick="PreHeartdis.delete(' + row.id + ')" id=""><i class="fa fa-arrows-alt"></i>&nbsp;确认诊断结果</button>';
                // }
            }
        }


    ];
};

/**
 * 检查是否选中
 */
PreHeartdis.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        PreHeartdis.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
PreHeartdis.openAddPreHeartdis = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/preHeartdis/preHeartdis_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
PreHeartdis.openPreHeartdisDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/preHeartdis/preHeartdis_update/' + PreHeartdis.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 确认诊断结果
 */
PreHeartdis.delete = function (id) {
        var ajax = new $ax(Feng.ctxPath + "/preHeartdis/delete", function (data) {
            Feng.success("诊断成功!");
            PreHeartdis.table.refresh();
        }, function (data) {
            Feng.error("诊断失败!" + data.responseJSON.message + "!");
        });
        ajax.set("preHeartdisId", id);
        ajax.start();
};




/**
 * 纠正诊断结果
 */
PreHeartdis.update = function (id) {
    var ajax = new $ax(Feng.ctxPath + "/preHeartdis/updateStatus", function (data) {
        Feng.success("纠正成功!");
        PreHeartdis.table.refresh();
    }, function (data) {
        Feng.error("纠正失败!" + data.responseJSON.message + "!");
    });
    ajax.set("preHeartdisId", id);
    ajax.start();
};

/**
 * 查询列表
 */
PreHeartdis.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PreHeartdis.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PreHeartdis.initColumn();
    var table = new BSTable(PreHeartdis.id, "/preHeartdis/list", defaultColunms);
    table.setPaginationType("client");
    PreHeartdis.table = table.init();
});

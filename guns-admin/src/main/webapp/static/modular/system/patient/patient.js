/**
 * 患者数据管理初始化
 */
var Patient = {
    id: "PatientTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Patient.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '联系方式', field: 'tel', visible: true, align: 'center', valign: 'middle'},
        {title: '身份证号', field: 'idCard', visible: true, align: 'center', valign: 'middle'},
        {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
        {title: '银行卡号', field: 'bankNo', visible: true, align: 'center', valign: 'middle'},
        {title: '是否住院', field: 'onHospital', visible: true, align: 'center', valign: 'middle'},
        {title: '上次检查时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
        {title: '入院时间', field: 'addTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Patient.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Patient.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加患者数据
 */
Patient.openAddPatient = function () {
    var index = layer.open({
        type: 2,
        title: '添加患者数据',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/patient/patient_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看患者数据详情
 */
Patient.openPatientDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '患者数据详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/patient/patient_update/' + Patient.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除患者数据
 */
Patient.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/patient/delete", function (data) {
            Feng.success("删除成功!");
            Patient.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("patientId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询患者数据列表
 */
Patient.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    // queryData['NameMaskType'] = $("NameMaskType").val();
    Patient.table.refresh({query: queryData});
};


$(function () {
    var defaultColunms = Patient.initColumn();
    var table = new BSTable(Patient.id, "/patient/list", defaultColunms);
    table.setPaginationType("client");
    Patient.table = table.init();
});

var ChartMgr = {
    chartId: "chart",
    myChart: null
};

var ChartCp = {
    chartId: "chartCp",
    myChart: null
};

var ChartExang = {
    chartId: "chartExang",
    myChart: null
};


var ChartAge = {
    chartId: "chartAge",
    myChart: null
};

var ChartThalach = {
    chartId: "chartThalach",
    myChart: null
};


var ChartChol = {
    chartId: "chartChol",
    myChart: null
};

/**
 * 生成性别饼图
 */
ChartMgr.generateSexScale = function () {
    var data = {
        labels: ["女性", "男性"],
        datasets: [{
            data: [],
            backgroundColor: [
                "#C4C4C4",
                "#FFE7BA"
            ]
        }]
    };

    var options = {
        title: {
            display: true,
            text: '心脏病患者性别分布拼图',
            fontSize: 20
        },
        legend: {
            display: true,
            position: 'center'
        }
    };

    //生成数据
    $.ajax({
        type: "GET",
        url: Feng.ctxPath + "/chart/sex",
        dataType: "json",
        async: false,
        success: function (result) {
            data.datasets[0].data = JSON.parse(result);
        }
    });

    var ctx = document.getElementById(this.chartId).getContext("2d");
    if (this.myChart != null) {
        this.myChart.destroy();
    }


    Chart.pluginService.register({
        beforeRender: function (chart) {
            if (chart.config.options.showAllTooltips) {
                // create an array of tooltips
                // we can't use the chart tooltip because there is only one tooltip per chart
                chart.pluginTooltips = [];
                chart.config.data.datasets.forEach(function (dataset, i) {
                    chart.getDatasetMeta(i).data.forEach(function (sector, j) {
                        chart.pluginTooltips.push(new Chart.Tooltip({
                            _chart: chart.chart,
                            _chartInstance: chart,
                            _data: chart.data,
                            _options: chart.options,
                            _active: [sector]
                        }, chart));
                    });
                });

                // turn off normal tooltips
                chart.options.tooltips.enabled = false;
            }
        },
        afterDraw: function (chart, easing) {
            if (chart.config.options.showAllTooltips) {
                // we don't want the permanent tooltips to animate, so don't do anything till the animation runs atleast once
                if (!chart.allTooltipsOnce) {
                    if (easing !== 1)
                        return;
                    chart.allTooltipsOnce = true;
                }

                // turn on tooltips
                chart.options.tooltips.enabled = true;
                Chart.helpers.each(chart.pluginTooltips, function (tooltip) {
                    tooltip.initialize();
                    tooltip.update();
                    // we don't actually need this since we are not animating tooltips
                    tooltip.pivot();
                    tooltip.transition(easing).draw();
                });
                chart.options.tooltips.enabled = false;
            }
        }
    })

    this.myChart = new Chart(ctx, {
        type: 'pie',
        data: data,
        options: {
            tooltips: {
                callbacks: {
                    label: function (tooltipItem, data) {
                        // var i = tooltipItem.index;
                        // var label = data.labels[i];
                        // label += ":";
                        // console.info(data.datasets)
                        // label = label + data.datasets[0].data[i];
                        // // label += Math.round(tooltipItem.yLabel * 100) / 100;
                        // return label + "%";
                        var label = data.datasets[0].data[tooltipItem.index];
                        return label + "%";
                    }
                }
            },
            showAllTooltips: true
        }
    });
};

/**
 * 生成胸痛饼图
 值为 '1': 典型的心绞痛
 值为 '2': 非典型的心绞痛
 值为 '3': 非心绞痛的疼痛
 值为 '4': 无临床症状
 */
ChartCp.generateCpScale = function () {
    var data = {
        labels: ["典型", "非典型", "非心绞痛的疼痛", "无症状"],
        datasets: [{
            data: [],
            backgroundColor: [
                "#FF4040",
                "#32CD32",
                "#FF1493",
                "#9C9C9C"
            ]
        }]
    };

    var options = {
        title: {
            display: true,
            text: '心脏病患者胸痛分布拼图',
            fontSize: 20
        },
        legend: {
            display: true,
            position: 'center'
        }
    };

    //生成数据
    $.ajax({
        type: "GET",
        url: Feng.ctxPath + "/chart/cp",
        dataType: "json",
        async: false,
        success: function (result) {
            data.datasets[0].data = JSON.parse(result);
        }
    });

    var ctx = document.getElementById(this.chartId).getContext("2d");
    if (this.myChart != null) {
        this.myChart.destroy();
    }


    Chart.pluginService.register({
        beforeRender: function (chart) {
            if (chart.config.options.showAllTooltips) {
                // create an array of tooltips
                // we can't use the chart tooltip because there is only one tooltip per chart
                chart.pluginTooltips = [];
                chart.config.data.datasets.forEach(function (dataset, i) {
                    chart.getDatasetMeta(i).data.forEach(function (sector, j) {
                        chart.pluginTooltips.push(new Chart.Tooltip({
                            _chart: chart.chart,
                            _chartInstance: chart,
                            _data: chart.data,
                            _options: chart.options,
                            _active: [sector]
                        }, chart));
                    });
                });

                // turn off normal tooltips
                chart.options.tooltips.enabled = false;
            }
        },
        afterDraw: function (chart, easing) {
            if (chart.config.options.showAllTooltips) {
                // we don't want the permanent tooltips to animate, so don't do anything till the animation runs atleast once
                if (!chart.allTooltipsOnce) {
                    if (easing !== 1)
                        return;
                    chart.allTooltipsOnce = true;
                }

                // turn on tooltips
                chart.options.tooltips.enabled = true;
                Chart.helpers.each(chart.pluginTooltips, function (tooltip) {
                    tooltip.initialize();
                    tooltip.update();
                    // we don't actually need this since we are not animating tooltips
                    tooltip.pivot();
                    tooltip.transition(easing).draw();
                });
                chart.options.tooltips.enabled = false;
            }
        }
    })

    this.myChart = new Chart(ctx, {
        type: 'pie',
        data: data,
        options: {
            tooltips: {
                callbacks: {
                    label: function (tooltipItem, data) {

                        var label = data.datasets[0].data[tooltipItem.index];
                        return label + "%";

                        // var i = tooltipItem.index;
                        // var label = data.labels[i];
                        // label += ":";
                        // console.info(data.datasets)
                        // label = label + data.datasets[0].data[i];
                        // return label + "%";
                    }
                }
            },
            showAllTooltips: true
        }
    });
};


/**
 * 是否运动时心绞痛
 * 1 心绞痛
 * 0 无症状
 */

ChartExang.generateExangScale = function () {
    var data = {
        labels: ["运动后心绞痛", "运动后无症状"],
        datasets: [{
            data: [],
            backgroundColor: [
                "#FF1493",
                "#9C9C9C"
            ]
        }]
    };

    var options = {
        title: {
            display: true,
            text: '运动时心绞痛患者分布拼图',
            fontSize: 20
        },
        legend: {
            display: true,
            position: 'center'
        }
    };

    //生成数据
    $.ajax({
        type: "GET",
        url: Feng.ctxPath + "/chart/exang",
        dataType: "json",
        async: false,
        success: function (result) {
            data.datasets[0].data = JSON.parse(result);
        }
    });

    var ctx = document.getElementById(this.chartId).getContext("2d");
    if (this.myChart != null) {
        this.myChart.destroy();
    }


    Chart.pluginService.register({
        beforeRender: function (chart) {
            if (chart.config.options.showAllTooltips) {
                // create an array of tooltips
                // we can't use the chart tooltip because there is only one tooltip per chart
                chart.pluginTooltips = [];
                chart.config.data.datasets.forEach(function (dataset, i) {
                    chart.getDatasetMeta(i).data.forEach(function (sector, j) {
                        chart.pluginTooltips.push(new Chart.Tooltip({
                            _chart: chart.chart,
                            _chartInstance: chart,
                            _data: chart.data,
                            _options: chart.options,
                            _active: [sector]
                        }, chart));
                    });
                });

                // turn off normal tooltips
                chart.options.tooltips.enabled = false;
            }
        },
        afterDraw: function (chart, easing) {
            if (chart.config.options.showAllTooltips) {
                // we don't want the permanent tooltips to animate, so don't do anything till the animation runs atleast once
                if (!chart.allTooltipsOnce) {
                    if (easing !== 1)
                        return;
                    chart.allTooltipsOnce = true;
                }

                // turn on tooltips
                chart.options.tooltips.enabled = true;
                Chart.helpers.each(chart.pluginTooltips, function (tooltip) {
                    tooltip.initialize();
                    tooltip.update();
                    // we don't actually need this since we are not animating tooltips
                    tooltip.pivot();
                    tooltip.transition(easing).draw();
                });
                chart.options.tooltips.enabled = false;
            }
        }
    })

    this.myChart = new Chart(ctx, {
        type: 'pie',
        data: data,
        options: {
            tooltips: {
                callbacks: {
                    label: function (tooltipItem, data) {
                        // var i = tooltipItem.index;
                        // var label = data.labels[i];
                        // label += ":";
                        // // console.info(data.datasets)
                        // // \label + data.datasets[0].data[i];
                        // return label + "%";
                        var label = data.datasets[0].data[tooltipItem.index];
                        return label + "%";
                    }
                }
            },
            showAllTooltips: true
        }
    });
};
/**
 生成患者年龄柱状图
 */
ChartAge.generateAgeChart = function () {
    var patientAgeData = {
        labels: [],
        datasets: [
            {
                label: '患病用户',
                backgroundColor: "rgba(26,109,148,40)",
                data: []
            },
        ]
    };

    //生成横轴标签
    $.ajax({
        type: "GET",
        url: Feng.ctxPath + "/chart/ageLabels",
        dataType: "json",
        async: false,
        success: function (result) {
            patientAgeData.labels = JSON.parse(result);
        }
    });

    //生成数据
    $.ajax({
        type: "GET",
        url: Feng.ctxPath + "/chart/age",
        dataType: "json",
        async: false,
        success: function (result) {
            patientAgeData.datasets[0].data = JSON.parse(result);
        }
    });


    // Chart.defaults.global.tooltipTemplate="<%= label %>#Pageview:+--------------------------------++<%= value %>";

    //显示数据
    Chart.pluginService.register({
        beforeRender: function (chart) {
            if (chart.config.options.showAllTooltips) {
                // create an array of tooltips
                // we can't use the chart tooltip because there is only one tooltip per chart
                chart.pluginTooltips = [];
                chart.config.data.datasets.forEach(function (dataset, i) {
                    chart.getDatasetMeta(i).data.forEach(function (sector, j) {
                        chart.pluginTooltips.push(new Chart.Tooltip({
                            _chart: chart.chart,
                            _chartInstance: chart,
                            _data: chart.data,
                            _options: chart.options,
                            _active: [sector]
                        }, chart));
                    });
                });

                // turn off normal tooltips
                chart.options.tooltips.enabled = false;
            }
        },
        afterDraw: function (chart, easing) {
            if (chart.config.options.showAllTooltips) {
                // we don't want the permanent tooltips to animate, so don't do anything till the animation runs atleast once
                if (!chart.allTooltipsOnce) {
                    if (easing !== 1)
                        return;
                    chart.allTooltipsOnce = true;
                }

                // turn on tooltips
                chart.options.tooltips.enabled = true;
                Chart.helpers.each(chart.pluginTooltips, function (tooltip) {
                    tooltip.initialize();
                    tooltip.update();
                    // we don't actually need this since we are not animating tooltips
                    tooltip.pivot();
                    tooltip.transition(easing).draw();
                });
                chart.options.tooltips.enabled = false;
            }
        }
    })

    //生成柱状图
    var ctx = document.getElementById(this.chartId);
    if (this.myChart != null) {
        this.myChart.destroy();
    }
    this.myChart = new Chart(ctx, {
        type: 'bar',
        data: patientAgeData,
        options: {

            "animation": {
                "duration": 1,
                "onComplete": function() {
                    var chartInstance = this.chart,
                        ctx = chartInstance.ctx;

                    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'bottom';

                    this.data.datasets.forEach(function(dataset, i) {
                        var meta = chartInstance.controller.getDatasetMeta(i);
                        meta.data.forEach(function(bar, index) {
                            var data = dataset.data[index];
                            ctx.fillText(data, bar._model.x, bar._model.y - 5);
                        });
                    });
                }
            },

            tooltips: {
                callbacks: {
                    label: function (tooltipItem, data) {
                        var i = tooltipItem.index;
                        // console.info(data.datasets)
                        var label = data.datasets[0].data[i];
                        return label + "名";
                    }
                }
            },
            showAllTooltips: false
        }
    });
};


/**
 * 患病用户心率分布图
 */
ChartThalach.generateThalachChart = function () {
    var patientThalachData = {
        labels: [],
        datasets: [
            {
                label: '患病用户',
                backgroundColor: "rgba(26,179,148,1)",
                data: []
            },
        ]
    };


        //生成横轴标签
    $.ajax({
        type: "GET",
        url: Feng.ctxPath + "/chart/thalachLabels",
        dataType: "json",
        async: false,
        success: function (result) {
            patientThalachData.labels = JSON.parse(result);
        }
    });

    //生成数据
    $.ajax({
        type: "GET",
        url: Feng.ctxPath + "/chart/thalach",
        dataType: "json",
        async: false,
        success: function (result) {
            patientThalachData.datasets[0].data = JSON.parse(result);
        }
    });


    //显示数据
    Chart.pluginService.register({
        beforeRender: function (chart) {
            if (chart.config.options.showAllTooltips) {
                chart.pluginTooltips = [];
                chart.config.data.datasets.forEach(function (dataset, i) {
                    chart.getDatasetMeta(i).data.forEach(function (sector, j) {
                        chart.pluginTooltips.push(new Chart.Tooltip({
                            _chart: chart.chart,
                            _chartInstance: chart,
                            _data: chart.data,
                            _options: chart.options,
                            _active: [sector]
                        }, chart));
                    });
                });
                chart.options.tooltips.enabled = false;
            }
        },
        afterDraw: function (chart, easing) {
            if (chart.config.options.showAllTooltips) {
                if (!chart.allTooltipsOnce) {
                    if (easing !== 1)
                        return;
                    chart.allTooltipsOnce = true;
                }
                // turn on tooltips
                chart.options.tooltips.enabled = true;
                Chart.helpers.each(chart.pluginTooltips, function (tooltip) {
                    tooltip.initialize();
                    tooltip.update();
                    tooltip.pivot();
                    tooltip.transition(easing).draw();
                });
                chart.options.tooltips.enabled = false;
            }
        }
    })

    //生成柱状图
    var ctx = document.getElementById(this.chartId);
    if (this.myChart != null) {
        this.myChart.destroy();
    }
    this.myChart = new Chart(ctx, {
        type: 'bar',
        data: patientThalachData,
        options: {
            "animation": {
                "duration": 1,
                "onComplete": function() {
                    var chartInstance = this.chart,
                        ctx = chartInstance.ctx;

                    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'bottom';

                    this.data.datasets.forEach(function(dataset, i) {
                        var meta = chartInstance.controller.getDatasetMeta(i);
                        meta.data.forEach(function(bar, index) {
                            var data = dataset.data[index];
                            ctx.fillText(data, bar._model.x, bar._model.y - 5);
                        });
                    });
                }
            },
            tooltips: {
                callbacks:
                    {
                        label: function (tooltipItem, data) {
                            var value = data.datasets[0].data[tooltipItem.index];
                            // console.info("data"+data.datasets);
                            console.info(tooltipItem);
                            tooltipItem="";
                            return value+"名";
                        }
                    }
            },
            showAllTooltips: false,
        }
    })
    ;
};


/**
 * 血清类固醇分布图
 */
ChartChol.generateCholChart = function () {
    var patientCholData = {
        labels: [],
        datasets: [
            {
                label: '患病用户',
                backgroundColor: "rgba(16,39,148,1)",
                data: []
            },
        ]
    };


    //生成横轴标签
    $.ajax({
        type: "GET",
        url: Feng.ctxPath + "/chart/cholLabels",
        dataType: "json",
        async: false,
        success: function (result) {
            patientCholData.labels = JSON.parse(result);
        }
    });

    //生成数据
    $.ajax({
        type: "GET",
        url: Feng.ctxPath + "/chart/chol",
        dataType: "json",
        async: false,
        success: function (result) {
            patientCholData.datasets[0].data = JSON.parse(result);
        }
    });


    //显示数据
    Chart.pluginService.register({
        beforeRender: function (chart) {
            if (chart.config.options.showAllTooltips) {
                chart.pluginTooltips = [];
                chart.config.data.datasets.forEach(function (dataset, i) {
                    chart.getDatasetMeta(i).data.forEach(function (sector, j) {
                        chart.pluginTooltips.push(new Chart.Tooltip({
                            _chart: chart.chart,
                            _chartInstance: chart,
                            _data: chart.data,
                            _options: chart.options,
                            _active: [sector]
                        }, chart));
                    });
                });
                chart.options.tooltips.enabled = false;
            }
        },
        afterDraw: function (chart, easing) {
            if (chart.config.options.showAllTooltips) {
                if (!chart.allTooltipsOnce) {
                    if (easing !== 1)
                        return;
                    chart.allTooltipsOnce = true;
                }
                // turn on tooltips
                chart.options.tooltips.enabled = true;
                Chart.helpers.each(chart.pluginTooltips, function (tooltip) {
                    tooltip.initialize();
                    tooltip.update();
                    tooltip.pivot();
                    tooltip.transition(easing).draw();
                });
                chart.options.tooltips.enabled = false;
            }
        }
    })

    //生成柱状图
    var ctx = document.getElementById(this.chartId);
    if (this.myChart != null) {
        this.myChart.destroy();
    }
    this.myChart = new Chart(ctx, {
        type: 'bar',
        data: patientCholData,
        options: {
            "animation": {
                "duration": 1,
                "onComplete": function() {
                    var chartInstance = this.chart,
                        ctx = chartInstance.ctx;

                    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'bottom';

                    this.data.datasets.forEach(function(dataset, i) {
                        var meta = chartInstance.controller.getDatasetMeta(i);
                        meta.data.forEach(function(bar, index) {
                            var data = dataset.data[index];
                            ctx.fillText(data, bar._model.x, bar._model.y - 5);
                        });
                    });
                }
            },
            tooltips: {
                callbacks:
                    {
                        label: function (tooltipItem, data) {
                            var value = data.datasets[0].data[tooltipItem.index];
                            // console.info("data"+data.datasets);
                            console.info(tooltipItem);
                            tooltipItem="";
                            return value+"名";
                        }
                    }
            },
            showAllTooltips: false,
        }
    })
    ;
};

$(function () {
    ChartMgr.generateSexScale();
    ChartCp.generateCpScale();
    ChartExang.generateExangScale();
    ChartAge.generateAgeChart();
    ChartThalach.generateThalachChart();
    ChartChol.generateCholChart();
});



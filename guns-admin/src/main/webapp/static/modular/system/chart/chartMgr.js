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
                        var i = tooltipItem.index;
                        var label = data.labels[i];
                        label += ":";
                        console.info(data.datasets)
                        label = label + data.datasets[0].data[i];
                        // label += Math.round(tooltipItem.yLabel * 100) / 100;
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
                        var i = tooltipItem.index;
                        var label = data.labels[i];
                        label += ":";
                        console.info(data.datasets)
                        label = label + data.datasets[0].data[i];
                        return label + "%";
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
                        var i = tooltipItem.index;
                        var label = data.labels[i];
                        label += ":";
                        console.info(data.datasets)
                        label = label + data.datasets[0].data[i];
                        return label + "%";
                    }
                }
            },
            showAllTooltips: true
        }
    });
};


$(function () {
    ChartMgr.generateSexScale();
    ChartCp.generateCpScale();
    ChartExang.generateExangScale();
});



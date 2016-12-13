/**
 * Created by zhouhaibin on 2016/9/19.
 */
var CenterReportClassifyManager = function(){
    var STATUS_CLOSED = 5;//关闭

    var CLASSIFY_STATUS_UNREADY = 0;//未进入分级
    var CLASSIFY_STATUS_UNASSIGNED = 1;//未分级（未领取）
    var CLASSIFY_STATUS_ASSIGNED = 2;//分级中（已领取）
    var CLASSIFY_STATUS_SUBMITTED = 3;//已分级

    var f;
    var start = 0;
    var limit = 10;
    var mode;
    var $container = $("#report-container");
    return{
        init: function() {
            f = this;
            Global.initTableLayout();
            Header.activeMenu("check-manager");
            f.initTemplate();
            mode = Global.mode;
            Global.initSelect($("#classify-status-select"));
            f.bindEvent();
            if (mode == "all") {
                $("#classify-status-select").select2("val", 1);
            } else {
                $("#classify-status-select").select2("val", 2);
            }
            f.load();
        },

        bindEvent: function() {
            $container.on("click", ".center-name", function () {
                var report = $.tmplItem($(this)).data;
                if (mode == "all" && report.classifyStatus == CLASSIFY_STATUS_UNASSIGNED && report.canceled == 0 && report.status != STATUS_CLOSED) {
                    f.receiveReport(report);
                }
                if (mode == "onlyMine" && (report.classifyStatus == CLASSIFY_STATUS_ASSIGNED || report.classifyStatus == CLASSIFY_STATUS_SUBMITTED)
                    && report.canceled == 0 && report.status != STATUS_CLOSED){
                    var url = "classifyCenterReport?id=" + report.id + "&type=" + Global.type;
                    window.open(url, "_self");
                }
                if (Global.isUserSystemAdmin()) {
                    var url = "classifyCenterReport?id=" + report.id + "&type=" + Global.type;
                    window.open(url, "_self");
                }
            });

            $("#search").on("click", function() {
                f.load();
            });
            $("#classify-status-select").on("change", function() {
                f.load();
            });
            $container.on("click", ".receive-center-report", function() {
                var report = $.tmplItem($(this)).data;
                f.receiveReport(report);
            });

            $container.on("click", ".sendback-center-report", function() {
                var report = $.tmplItem($(this)).data;
                if (!window.confirm("确认要退领此分级任务吗？"))
                    return;
                Ajax.call({
                    url: "sendbackClassifyCenterReport",
                    p: {
                        type: Global.type,
                        id: report.id
                    },
                    f: function(response) {
                        Notify.info("退领任务成功");
                        f.load();
                    }
                });
            });
        },

        initTemplate: function() {
            var centerReportTemplate =
                '<tr id="${id}">' +
                    '<td>${projectId}</td>' +
                    '<td>${projectName}</td>' +
                    '<td>${stageName}</td>' +
                    '<td>' +
                        '<a href="javascript:void(0)" class="center-name">${centerName}</a>' +
                    '</td>' +
                    '<td>${$item.getClassifyStatusString($item.data)}</td>' +
                    '<td>${Global.getUserName($item.data.classifyUserId)}</td>' +
                    '<td>${submittedToCheckTime}</td>' +
                    '<td>${classifyAssignedTime}</td>' +
                    '<td>${classifySubmittedTime}</td>' +
                    '<td>' +
                        '{{if classifyStatus == 1 && canceled == 0 && status != 5}}' +
                        '<a title="领取" href="javascript:void(0)" class="table-operation-icon receive-center-report"><i class="glyphicon glyphicon-download-alt"></i></a>' +
                        '{{/if}}' +
                        '{{if classifyStatus == 2 && canceled == 0 && status != 5}}' +
                        '<a title="退领" href="javascript:void(0)" class="table-operation-icon sendback-center-report"><i class="glyphicon glyphicon-transfer"></i></a>' +
                        '{{/if}}' +
                        '<a title="分级" href="classifyCenterReport?id=${id}&type=' + Global.type + '" class="table-operation-icon classify-center-report"><i class="glyphicon glyphicon-check"></i></a>' +
                    '</td>' +
                '</tr>';
            $.template("centerReportTemplate", centerReportTemplate);
        },

        receiveReport: function(report) {
            if (!window.confirm("确认要领取此分级任务吗？"))
                return;
            Ajax.call({
                url: "receiveClassifyCenterReport",
                p: {
                    type: Global.type,
                    id: report.id
                },
                f: function(response) {
                    Notify.info("领取任务成功");
                    f.load();
                }
            });
        },

        load: function() {
            //var projectId = GlobalConstants.EMPTY_OBJECT;
            //var stageId = GlobalConstants.EMPTY_OBJECT;
            //var centerId = GlobalConstants.EMPTY_OBJECT;
            var classifyStatus = parseInt($("#classify-status-select").select2("val"));
            Ajax.call({
                url: "loadCenterReportsToClassify",
                p: {
                    //projectId: projectId,
                    //stageId: stageId,
                    //centerId: centerId,
                    type: Global.type,
                    keywords: $("#keywords").val(),
                    mode: mode,
                    classifyStatus: classifyStatus,
                    start: start,
                    limit: limit
                },
                f: function(response) {
                    f.render(response.result);
                    Global.refreshControlsByPrivilege();
                }
            })

        },

        render: function(result) {
            $container.html($.tmpl("centerReportTemplate", result.list, {
                getClassifyStatusString: function (item) {
                    return GlobalConstants.CENTER_REPORT_CLASSIFY_STATUS[item.classifyStatus];
                }
            }));
            if (mode == "all") {
                $(".sendback-center-report.table-operation-icon").remove();
                if (!Global.isUserSystemAdmin())
                    $(".classify-center-report.table-operation-icon").remove();
            } else {
                $(".receive-center-report.table-operation-icon").remove();
            }

            $("#pagination").MyPagination({
                currentPage: result.currentPage,
                resultsPerPage: result.limit,
                totalPage: result.totalPage,
                totalCount: result.totalCount,
                callback: {
                    onGotoPage:function(para) {
                        limit = para.limit;
                        start = (para.page - 1) * limit;
                        f.load();
                    }
                }
            });
        },

        empty: null
    }
}();

$(document).ready(function() {
    CenterReportClassifyManager.init();
});


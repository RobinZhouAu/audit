/**
 * Created by zhouhaibin on 2016/9/19.
 */
var ReportCheckManager = function () {
    var STATUS_CLOSED = 5;//关闭

    var CHECK_STATUS_UNREADY = 0;//未进入评审
    var CHECK_STATUS_UNASSIGNED = 1;//未评审（未领取）
    var CHECK_STATUS_ASSIGNED = 2;//评审中（已领取）
    var CHECK_STATUS_SUBMITTED = 3;//已评审

    var f;
    var start = 0;
    var limit = 10;
    var mode;
    var $container = $("#report-container");
    return {
        init: function () {
            f = this;
            Global.initTableLayout();
            Header.activeMenu("check-manager");
            f.initTemplate();
            mode = Global.mode;
            Global.initSelect($("#check-status-select"));
            f.bindEvent();
            if (mode == "all") {
                $("#check-status-select").select2("val", 1);
            } else {
                $("#check-status-select").select2("val", 2);
            }
            f.load();
        },

        bindEvent: function () {
            $("#search").on("click", function () {
                f.load();
            });
            $("#check-status-select").on("change", function () {
                f.load();
            });
            $container.on("click", ".receive-report", function () {
                var report = $.tmplItem($(this)).data;
                f.receiveReport(report);
            });

            $container.on("click", ".center-name", function () {
                var report = $.tmplItem($(this)).data;
                if (mode == "all" && report.checkStatus == CHECK_STATUS_UNASSIGNED && report.canceled == 0 && report.status != STATUS_CLOSED) {
                    f.receiveReport(report);
                }
                if (mode == "onlyMine" && report.checkStatus == CHECK_STATUS_ASSIGNED && report.canceled == 0 && report.status != STATUS_CLOSED){
                    var url = "checkReport?id=" + report.id + "&type=" + Global.type;
                    window.open(url, "_self");
                }
            });

            $container.on("click", ".project-name", function () {
                var report = $.tmplItem($(this)).data;
                if (mode == "all" && report.checkStatus == CHECK_STATUS_UNASSIGNED && report.canceled == 0 && report.status != STATUS_CLOSED) {
                    f.receiveReport(report);
                }
                if (mode == "onlyMine" && report.checkStatus == CHECK_STATUS_ASSIGNED && report.canceled == 0 && report.status != STATUS_CLOSED){
                    var url = "checkReport?id=" + report.id + "&type=" + Global.type;
                    window.open(url, "_self");
                }
            });

            $container.on("click", ".sendback-report", function () {
                var report = $.tmplItem($(this)).data;
                if (!window.confirm("确认要退领此评审任务吗？"))
                    return;
                Ajax.call({
                    url: "sendbackCheckReport",
                    p: {
                        type: Global.type,
                        id: report.id
                    },
                    f: function (response) {
                        Notify.info("退领任务成功");
                        f.load();
                    }
                });
            });
        },

        initTemplate: function () {
            var reportTemplate;
            if (Global.type == "CenterReport") {
                reportTemplate =
                    '<tr id="${id}">' +
                    '<td>${projectId}</td>' +
                    '<td>${projectName}</td>' +
                    '<td>${stageName}</td>' +
                    '<td>' +
                    '<a href="javascript:void(0)" class="center-name">${centerName}</a>' +
                    '</td>' +
                    '<td>${$item.getCheckStatusString($item.data)}</td>' +
                    '<td>${Global.getUserName($item.data.checkUserId)}</td>' +
                    '<td>${submittedToCheckTime}</td>' +
                    '<td>${checkAssignedTime}</td>' +
                    '<td>${checkSubmittedTime}</td>' +
                    '<td>' +
                    '{{if checkStatus == 1 && canceled == 0 && status != 5}}' +
                    '<a title="领取" href="javascript:void(0)" class="table-operation-icon receive-report"><i class="glyphicon glyphicon-download-alt"></i></a>' +
                    '{{/if}}' +
                    '{{if checkStatus == 2 && canceled == 0 && status != 5}}' +
                    '<a title="退领" href="javascript:void(0)" class="table-operation-icon sendback-report"><i class="glyphicon glyphicon-transfer"></i></a>' +
                    '<a title="评审" href="checkReport?id=${id}&type=' + Global.type + '" class="table-operation-icon check-report"><i class="glyphicon glyphicon-check"></i></a>' +
                    '{{/if}}' +
                    '{{if checkStatus == 3}}' +
                    '<a title="详情" href="checkReport?id=${id}&type=' + Global.type + '" class="table-operation-icon report-detail"><i class="glyphicon glyphicon-list-alt"></i></a>' +
                    '{{/if}}' +
                    '</td>' +
                    '</tr>';
            } else {
                reportTemplate =
                    '<tr id="${id}">' +
                    '<td>${projectId}</td>' +
                    '<td>' +
                    '<a href="javascript:void(0)" class="project-name">${projectName}</a>' +
                    '</td>' +
                    '<td>${stageName}</td>' +
                    '<td>${$item.getCheckStatusString($item.data)}</td>' +
                    '<td>${Global.getUserName($item.data.checkUserId)}</td>' +
                    '<td>${submittedToCheckTime}</td>' +
                    '<td>${checkAssignedTime}</td>' +
                    '<td>${checkSubmittedTime}</td>' +
                    '<td>' +
                    '{{if checkStatus == 1 && canceled == 0 && status != 5}}' +
                    '<a title="领取" href="javascript:void(0)" class="table-operation-icon receive-report"><i class="glyphicon glyphicon-download-alt"></i></a>' +
                    '{{/if}}' +
                    '{{if checkStatus == 2 && canceled == 0 && status != 5}}' +
                    '<a title="退领" href="javascript:void(0)" class="table-operation-icon sendback-report"><i class="glyphicon glyphicon-transfer"></i></a>' +
                    '<a title="评审" href="checkReport?id=${id}&type=' + Global.type + '" class="table-operation-icon check-report"><i class="glyphicon glyphicon-check"></i></a>' +
                    '{{/if}}' +
                    '{{if checkStatus == 3}}' +
                    '<a title="详情" href="checkReport?id=${id}&type=' + Global.type + '" class="table-operation-icon report-detail"><i class="glyphicon glyphicon-list-alt"></i></a>' +
                    '{{/if}}' +
                    '</td>' +
                    '</tr>';

            }
            $.template("reportTemplate", reportTemplate);
        },

        receiveReport: function (report) {
            if (!window.confirm("确认要领取此评审任务吗？"))
                return;
            Ajax.call({
                url: "receiveCheckReport",
                p: {
                    type: Global.type,
                    id: report.id
                },
                f: function (response) {
                    Notify.info("领取任务成功");
                    f.load();
                }
            });
        },

        load: function () {
            //var projectId = GlobalConstants.EMPTY_OBJECT;
            //var stageId = GlobalConstants.EMPTY_OBJECT;
            //var centerId = GlobalConstants.EMPTY_OBJECT;
            var checkStatus = parseInt($("#check-status-select").select2("val"));
            Ajax.call({
                url: "loadReportsToCheck",
                p: {
                    //projectId: projectId,
                    //stageId: stageId,
                    //centerId: centerId,
                    type: Global.type,
                    keywords: $("#keywords").val(),
                    mode: mode,
                    checkStatus: checkStatus,
                    start: start,
                    limit: limit
                },
                f: function (response) {
                    f.render(response.result);
                    Global.refreshControlsByPrivilege();
                }
            })

        },

        render: function (result) {
            $container.html($.tmpl("reportTemplate", result.list, {
                getCheckStatusString: function (item) {
                    return GlobalConstants.CENTER_REPORT_CHECK_STATUS[item.checkStatus];
                }
            }));
            if (mode == "all") {
                $(".sendback-report.table-operation-icon").remove();
                $(".check-report.table-operation-icon").remove();
            } else {
                $(".receive-report.table-operation-icon").remove();
            }

            $("#pagination").MyPagination({
                currentPage: result.currentPage,
                resultsPerPage: result.limit,
                totalPage: result.totalPage,
                totalCount: result.totalCount,
                callback: {
                    onGotoPage: function (para) {
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

$(document).ready(function () {
    ReportCheckManager.init();
});


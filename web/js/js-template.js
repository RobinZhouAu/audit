/**
 * Created by zhouhaibin on 2016/9/19.
 */
var ObjectManager = function(){
    var f;
    var start = 0;
    var limit = 10;
    var $container = $("#object-container");
    return{
        init: function() {
            f = this;
            Header.activeMenu("task-manager");
            f.initTemplate();
            f.bindEvent();
            f.load();
        },

        initTemplate: function() {
            var objectTemplate =
                '<tr id="{$id}">' +
                '<td>${projectId}</td>' +
                '<td>${projectName}</td>' +
                '<td>${stageName}</td>' +
                '<td><a target="_blank" title="进入稽查模块列表页面" href="toTaskDetail?id=${id}">${centerName}</a></td>' +
                '<td>${$item.getStatusString($item.data)}</td>' +
                '<td>${Global.getMemberNames($item.data.memberIds)}</td>' +
                '<td>${projectCreated}</td>' +
                '<td>' +
                '<a title="详情" href="toTaskDetail?id=${id}" class="table-operation-icon task-detail"><i class="glyphicon glyphicon-list-alt"></i></a>' +
                '<a title="生成原始版稽查记录表" href="javascript:void(0)" class="table-operation-icon create-original-report"><i class="glyphicon glyphicon-th-list"></i></a>' +
                '<a title="生成单中心报告" href="javascript:void(0)" class="table-operation-icon create-center-report"><i class="glyphicon glyphicon-file"></i></a>' +
                '<a title="删除" href="javascript:void(0)" class="table-operation-icon delete-task"><i class="glyphicon glyphicon-remove-circle"></i></a>' +
                '</td>' +
                '</tr>';
            $.template("objectTemplate", objectTemplate);

        },

        bindEvent: function() {
            $container.on("click", ".", function() {
                var object = $.tmplItem($(this)).data;
                if (!window.confirm("您确定要吗？"))
                    return;
                Ajax.call({
                    url: "",
                    p: {
                        id: object.id
                    },
                    f: function(response) {
                        Notify.info("成功");
                    }
                });
            });
        },

        load: function() {
            Ajax.call({
                url: "",
                p: {
                    keywords: $("#keywords").val(),
                    start: start,
                    limit: limit
                },
                f: function(response) {
                    f.render(response.result);
                }
            });
        },

        render: function(result) {
            $container.html($.tmpl("objectTemplate", result.list, {
                getStatusString: function (item) {
                    return GlobalConstants.REPORT_STATUS[item.status];
                }
            }));
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
    ObjectManager.init();
});


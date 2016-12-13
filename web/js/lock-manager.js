/**
 * Created by zhouhaibin on 2016/9/19.
 */
var LockManager = function(){
    var RESOURCE_TYPE_STRING = ["任务模块", "发现", "单中心报告", "项目阶段报告"];
    var f;
    //var start = 0;
    //var limit = 10;
    var $container = $("#lock-container");
    return{
        init: function() {
            f = this;
            //Header.activeMenu("task-manager");
            f.initTemplate();
            f.bindEvent();
            f.load();
        },

        initTemplate: function() {
            var lockTemplate =
                '<tr id="{$id}">' +
                '<td>${resourceId}</td>' +
                '<td>${userId}</td>' +
                '<td>${Global.getUserName($item.data.userId)}</td>' +
                '<td>${$item.getResourceTypeString($item.data)}</td>' +
                '<td>${lastUpdateTime}</td>' +
                '<td>' +
                '<a title="解锁" href="javascript:void(0)" class="table-operation-icon unlock"><i class="glyphicon glyphicon-remove-circle"></i></a>' +
                '</td>' +
                '</tr>';
            $.template("lockTemplate", lockTemplate);

        },

        bindEvent: function() {
            $container.on("click", ".unlock", function() {
                var lock = $.tmplItem($(this)).data;
                if (!window.confirm("您确定要解锁吗？"))
                    return;
                Ajax.call({
                    url: "unlockResource",
                    p: {
                        id: lock.resourceId
                    },
                    f: function(response) {
                        Notify.info("解锁成功");
                        f.load();
                    }
                });
            });
        },

        load: function() {
            Ajax.call({
                url: "loadLocks",
                p: {
                    //keywords: $("#keywords").val(),
                    //start: start,
                    //limit: limit
                },
                f: function(response) {
                    f.render(response.list);
                }
            });
        },

        render: function(list) {
            $container.html($.tmpl("lockTemplate", list, {
                getResourceTypeString: function (item) {
                    return RESOURCE_TYPE_STRING[item.resourceType];
                }
            }));
        },

        empty: null
    }
}();

$(document).ready(function() {
    LockManager.init();
});


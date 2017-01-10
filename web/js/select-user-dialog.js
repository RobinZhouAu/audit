/**
 * Created by zhouhaibin on 2016/9/22.
 */
var SelectUserDialog = function(){
    var f;
    var dialog;
    return{
        init: function() {
            f = this;
            var selectUserTemplate =
                '<optgroup label="${fullPathName}">' +
                    '{{each(i, user) users}}' +
                    '<option value="${user.id}">${user.name}</option>' +
                    '{{/each}}' +
                '</optgroup>';
            $.template("selectUserTemplate", selectUserTemplate);
        },

        show: function(options) {
            f.options = options;
            f.singleSelectUser = f.options.singleSelect;
            if (dialog == undefined) {
                var url = "audit/html/select-user-dialog.html";
                var res = nunjucks.render(url);
                $('body').append(res);
                dialog = $('#dialog-select-user');
                f.initMultiSelect();
                f.doNothingAfterSelect = false;
                dialog.find(".multi-select-user").multiSelect( {
                    selectableHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='输入相关信息进行检索'>",
                    selectionHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='输入相关信息进行检索'>",
                    afterInit: function(ms){
                        var that = this,
                            $selectableSearch = that.$selectableUl.prev(),
                            $selectionSearch = that.$selectionUl.prev(),
                            selectableSearchString = '#'+that.$container.attr('id')+' .ms-elem-selectable:not(.ms-selected)',
                            selectionSearchString = '#'+that.$container.attr('id')+' .ms-elem-selection.ms-selected';

                        that.qs1 = $selectableSearch.quicksearch(selectableSearchString).on('keydown', function(e){
                            if (e.which === 40){
                                that.$selectableUl.focus();
                                return false;
                            }
                        });

                        that.qs2 = $selectionSearch.quicksearch(selectionSearchString).on('keydown', function(e){
                            if (e.which == 40){
                                that.$selectionUl.focus();
                                return false;
                            }
                        });
                    },
                    afterSelect: function(values) {
                        if (!f.doNothingAfterSelect) {
                            f.doNothingAfterSelect = true;
                            if (f.singleSelectUser == "true") {
                                dialog.find(".multi-select-user").multiSelect('deselect_all');
                                dialog.find(".multi-select-user").multiSelect('select', values);
                            }
                            f.doNothingAfterSelect = false;
                        }
                        this.qs1.cache();
                        this.qs2.cache();
                    },

                    afterDeselect: function(){
                        this.qs1.cache();
                        this.qs2.cache();
                    },
                    selectableOptgroup: false
                });
                dialog.on("click", ".dialog-ok", function() {
                    var selectedUsers = [];
                    if (f.options.callback != undefined) {
                        var $selectedUsers = dialog.find("option:selected");
                        $selectedUsers.each(function(index, $selectedUser){
                            var user = {
                                id: $($selectedUser).prop("value"),
                                name: $($selectedUser).html()
                            };
                            selectedUsers.push(user);
                        });
                        f.options.callback(selectedUsers, f.options.callbackPara);
                    }
                });
            }
            f.showDialog();
            if (f.options.selectedUserIdList != undefined && f.options.selectedUserIdList.length > 0) {
                f.doNothingAfterSelect = true;
                dialog.find(".multi-select-user").multiSelect('select', f.options.selectedUserIdList);
                f.doNothingAfterSelect = false;
            } else {
                dialog.find(".multi-select-user").multiSelect('deselect_all');
            }
        },

        initMultiSelect: function() {
            f.buildDepartmentUser();
            dialog.find(".multi-select-user").html($.tmpl("selectUserTemplate",  Global.allDepartments));
        },

        buildDepartmentUser: function() {
            for (var i = 0; i < Global.allDepartments.length; i ++) {
                var department = Global.allDepartments[i];
                department.users = [];
                for (var j = 0; j < Global.allUsers.length; j ++) {
                    var user = Global.allUsers[j];
                    if (user.departmentId == department.id) {
                        department.users.push(user);
                    }
                }
            }
        },

        showDialog: function() {
            dialog.modal({
                backdrop:'static',
                keyboard:true,
                show:true
            });
        },

        empty: null
    }
}();

$(document).ready(function() {
    SelectUserDialog.init();
});


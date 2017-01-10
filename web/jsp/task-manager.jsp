<%--
  Created by IntelliJ IDEA.
  User: zhouhaibin
  Date: 2016/9/19
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<html lang="en" class="no-js">
<head>
    <title>稽查任务管理</title>
    <%@include file="jspbase.jsp" %>
</head>
<!-- BEGIN BODY -->
<body class="page-header-fixed">
    <%@include file="header.jsp" %>
    <div class="container-fluid">
        <div class="page-container">
            <ol class="breadcrumb">
                <li>稽查管理</li>
                <li class="active">稽查任务</li>
            </ol>
            <div class="row">
                <div class="col-md-10 col-sm-12">
                    <div class="row">
                        <div class="col-md-6 col-sm-12">
                            <div class="input-group input-medium">
                                <input id="keywords" type="text" placeholder="输入相关信息进行检索" class="form-control">
                                <span id="search" class="input-group-addon">
                                    <i class="fa fa-search"></i>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-12 text-right">
                            <button id="show-modify-records" pid="SYSTEM_ADMIN" class="btn default" type="button">查看修改记录</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="margin-top-10">
                <table tableId="TaskManager" class="table table-striped table-bordered table-hover dataTable">
                    <thead>
                    <tr>
                        <th>项目编号</th>
                        <th>项目名称</th>
                        <th>项目阶段</th>
                        <th>中心名称</th>
                        <th>报告状态</th>
                        <th>项目成员</th>
                        <th>创建日期</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="task-container">
                    </tbody>
                </table>
                <div id="pagination"></div>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
    <script src="js/data-structure.js" type="text/javascript"></script>
    <script src="js/task-manager.js" type="text/javascript"></script>
</body>

</html>

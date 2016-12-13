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
    <title>Object管理</title>
    <%@include file="jspbase.jsp" %>
</head>
<body class="page-header-fixed">
    <%@include file="header.jsp" %>
    <div class="container-fluid">
        <div class="page-container">
            <ol class="breadcrumb">
                <li>稽查管理</li>
                <li class="active">稽查任务</li>
            </ol>
            <form class="navbar-form navbar-right" onkeydown="if(event.keyCode==13)return false;" >
                <div class="form-group">
                    <input id="keywords" type="text" class="form-control" placeholder="输入搜索关键字">
                </div>
                <button id="search" class="btn btn-default">搜索</button>
            </form>
            <div class="margin-top-10">
                <table class="table table-striped table-bordered table-hover dataTable">
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
                    <tbody id="object-container">
                    </tbody>
                </table>
                <div id="pagination"></div>
            </div>
        </div>
    </div>

    <script src="js/project-manager.js" type="text/javascript"></script>
</body>

</html>

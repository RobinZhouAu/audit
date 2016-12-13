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
    <title>资源锁管理</title>
    <%@include file="jspbase.jsp" %>
</head>
<body class="page-header-fixed">
    <%--<%@include file="header.jsp" %>--%>
    <div class="container-fluid">
        <div class="page-container">
            <%--<ol class="breadcrumb">--%>
                <%--<li>稽查管理</li>--%>
                <%--<li class="active">稽查任务</li>--%>
            <%--</ol>--%>
            <%--<form class="navbar-form navbar-right" onkeydown="if(event.keyCode==13)return false;" >--%>
                <%--<div class="form-group">--%>
                    <%--<input id="keywords" type="text" class="form-control" placeholder="输入搜索关键字">--%>
                <%--</div>--%>
                <%--<button id="search" class="btn btn-default">搜索</button>--%>
            <%--</form>--%>
            <div class="margin-top-10">
                <table class="table table-striped table-bordered table-hover dataTable">
                    <thead>
                    <tr>
                        <th>被锁资源id</th>
                        <th>用户Id</th>
                        <th>用户名称</th>
                        <th>资源类型</th>
                        <th>最后更新时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="lock-container">
                    </tbody>
                </table>
                <%--<div id="pagination"></div>--%>
            </div>
        </div>
    </div>

    <script src="js/lock-manager.js" type="text/javascript"></script>
</body>

</html>

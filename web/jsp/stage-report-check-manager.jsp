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
    <%@include file="jspbase.jsp" %>
    <title>项目阶段稽查报告评审</title>
</head>
<body class="page-header-fixed">
    <%@include file="header.jsp" %>
    <div class="container-fluid">
        <div class="page-container">
            <ol class="breadcrumb">
                <li>评审任务</li>
                <li>单中心稽查报告评审</li>
                <c:if test="${mode == 'all'}">
                    <li class="active">项目阶段审核任务</li>
                </c:if>
                <c:if test="${mode == 'onlyMine'}">
                    <li class="active">我的项目阶段审核任务</li>
                </c:if>
            </ol>
            <div class="col-md-10 col-sm-12">
                <form class="form-horizontal" role="form">
                    <div class="row">
                        <div class="col-md-8 col-sm-12">
                            <form class="form-horizontal" role="form">
                                <div class="form-group col-md-6">
                                    <label>评审情况</label>
                                    <select id="check-status-select">
                                        <option value="-1">全部</option>
                                        <c:if test="${mode == 'all'}">
                                        <option value="1">待评审</option>
                                        </c:if>
                                        <option value="2">评审中</option>
                                        <option value="3">已评审</option>
                                    </select>
                                </div>
                                <div class="input-group input-medium col-md-6">
                                    <input id="keywords" type="text" placeholder="输入相关信息进行检索" class="form-control">
                                    <span id="search" class="input-group-addon">
                                        <i class="fa fa-search"></i>
                                    </span>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-4 col-sm-12 text-right">
                            <c:if test="${mode == 'all'}">
                            <a href="toMyReportCheckManager?type=${type}">我的项目</a>
                            </c:if>
                            <c:if test="${mode == 'onlyMine'}">
                            <a href="toReportCheckManager?type=${type}">待审区</a>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
            <div class="margin-top-10">
                <table tableId="StageReportCheckManager" class="table table-striped table-bordered table-hover dataTable">
                    <thead>
                        <tr>
                            <th>项目编号</th>
                            <th>项目名称</th>
                            <th>项目阶段</th>
                            <th>评审状态</th>
                            <th>评审人</th>
                            <th>提交审阅时间</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="report-container">
                    </tbody>
                </table>
                <div id="pagination"></div>
            </div>
        </div>
    </div>
    <script src="js/report-check-manager.js" type="text/javascript"></script>
</body>

</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>电梯事务-首页</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <%--<link href="https://cdn.bootcss.com/weui/1.1.1/style/weui.css" rel="stylesheet">--%>
    <%--<script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>--%>
    <script src="<%=request.getContextPath()%>/js/jquery-1.12.1.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<a href="/toJsp/search_elevator" class="weui-btn weui-btn_primary">查询电梯</a>
<br>
<a href="/toJsp/complaint_fill_in" class="weui-btn weui-btn_warn">投诉电梯</a>
<br>
<a href="/user/myComplaint" class="weui-btn weui-btn_default">我的投诉</a>
<br>
<%--<a href="/wechat/wclogin" class="weui-btn weui-btn_plain-default">微信登录</a>--%>

</body>
</html>
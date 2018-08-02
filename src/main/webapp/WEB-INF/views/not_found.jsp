<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>找不到对象</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
</head>
<body>
    <h3>${message}</h3>
    <span><a href="javascript:history.back(-1)" class="weui-btn_mini">返回</a></span>
</body>
</html>

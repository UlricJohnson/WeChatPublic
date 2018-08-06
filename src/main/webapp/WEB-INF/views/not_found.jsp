<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>找不到对象</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
</head>
<body>
<div>
    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-warn weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">${msg}</h2>
            <%--<p class="weui-msg__desc">
                内容详情，可根据实际需要安排，如果换行则不超过规定长度，居中展现
                <a href="javascript:void(0);">文字链接</a>
                </p>--%>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <%--<a href="javascript:history.back();" class="weui-btn weui-btn_primary">推荐操作</a>--%>
                <a href="javascript:history.back();" class="weui-btn weui-btn_default">返回</a>
            </p>
        </div>
    </div>
</div>

<%--<h3>${msg}</h3>
<span><a href="javascript:history.back(-1)" class="weui-btn_mini">返回</a></span>--%>
</body>
</html>

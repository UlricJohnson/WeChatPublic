<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>投诉失败</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>
</head>
<body>
<div style="height: 100%;">
    <div class="weui-msg" style="padding-top: 20%;">
        <div class="weui-msg__icon-area"><i class="weui-icon-warn weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">反馈失败</h2>
            <p class="weui-msg__desc">
                用户：${username} 您好！
                由于系统异常，您对电梯
                <i style="color: #7B68EE;">${certificate}</i>
                &nbsp;的反馈失败，请稍后重试！
                <%--<a href="javascript:void(0);">文字链接</a>--%>
            </p>
        </div>
    </div>
</div>

<%--<div><i class="weui-icon-warn weui-icon_msg-primary"></i></div>
<div>
    <h2>用户：${username} 您好！</h2>
    <p>
        由于系统异常，您对电梯
        <i style="color: #7B68EE;">${certificate}</i>
        &nbsp;的反馈失败，请稍后重试！
    </p>
</div>--%>
</body>
</html>

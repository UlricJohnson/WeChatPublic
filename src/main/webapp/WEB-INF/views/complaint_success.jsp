<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>投诉成功</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>
</head>
<body>
<div><i class="weui-icon-success weui-icon_msg"></i></div>
<div>
    <h2>用户：${username} 您好！</h2>
    <p>
        您已完成对电梯
        &nbsp;<i style="color: #7B68EE;">${certificate}</i>&nbsp;
        的反馈！我们会尽力改善，感谢对本系统的使用。
    </p>
</div>
</body>
</html>

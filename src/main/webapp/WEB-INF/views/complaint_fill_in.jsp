<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投诉电梯</title>
</head>
<body>
<div>
    <form action="/complaint/toComplaint_fillIn" method="post">
        <label for="certificate">请输入您要投诉的电梯使用证编号：</label>
        <input id="certificate" type="text" name="certificateOfUse">
        <input type="submit" value="提交">
    </form>
</div>
</body>
</html>

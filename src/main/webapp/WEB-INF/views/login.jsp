<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>
</head>
<body>

<%--<div class="weui-cells__title">表单</div>--%>
<form action="/user/login" method="post">
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <span style="color: red;">*</span>
            <div class="weui-cell__hd">
                <label for="username" class="weui-label">用户名</label>
            </div>
            <div class="weui-cell__bd">
                <input id="username" class="weui-input" type="text" name="username"
                       placeholder="请输入用户名"/>
            </div>
        </div>
        <div class="weui-cell">
            <span style="color: red;">*</span>
            <div class="weui-cell__hd">
                <label for="contactNum" class="weui-label">联系电话</label>
            </div>
            <div class="weui-cell__bd">
                <input id="contactNum" class="weui-input" type="tel" name="contactNum"
                       pattern="^[1][3,4,5,7,8][0-9]{9}$" placeholder="请输入手机号码"/>
            </div>
        </div>
        <div class="weui-btn-area">
            <a id="loginBtn" href="javascript:;" class="weui-btn weui-btn_primary">登录</a>
        </div>
    </div>
</form>

<script>
    $(function () {
        // 检查输入框，测试时的版本（不用使用正则验证手机号），部署前添加下面
        $("#loginBtn").click(function () {
            var flag = true;

            // 检查是否填写
            $("input").each(function () {
                var value = $(this).val();
                if (value == null || value == "") {
                    alert($(this).attr("placeholder"));
                    flag = false;
                    return false; // 结束 each 循环，结束本次循环返回true
                }
            });

            // 正则验证手机号格式（部署前记得解除注释）
            /*var number = $("#contactNum").val();
            var numReg = /^[1][3,4,5,7,8][0-9]{9}$/;
            if (!numReg.test(number)) {
                alert("手机号码格式错误");
                flag = false;
            }*/

            if (flag) {
                $("form")[0].submit();
            }
        });

        // 检查输入框
        /*$("#loginBtn").click(function () {
            // 检查手机号码是否填写正确
            var number = $("#contactNum").val();

            var numReg = /^[1][3,4,5,7,8][0-9]{9}$/;

            if (!numReg.test(number)) {
                alert("手机号码格式错误");
            } else {
                $("form")[0].submit();
            }
        });*/
    });
</script>

</body>
</html>

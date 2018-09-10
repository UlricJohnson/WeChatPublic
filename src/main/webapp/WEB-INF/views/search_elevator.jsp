<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查找电梯</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/toastr/toastr.min.css">
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/jquery-mobile/jquery.mobile-1.4.5.min.css">--%>
    <script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>
    <%--<script src="<%=request.getContextPath()%>/jquery-mobile/jquery.mobile-1.4.5.min.js"></script>--%>
    <script src="<%=request.getContextPath()%>/toastr/toastr.min.js"></script>
</head>
<body>
<%--<p>请输入电梯使用证编号：</p>--%>

<div style="width: 100%; height: 50%;">
    <%-- 电梯的图片 --%>
    <img src="<%=request.getContextPath()%>/img/headImg.png" alt="图片找不到了"
         style="display: block; margin: auto; padding-top: 80px;">
</div>

<div style="width: 100%; height: 50%; position: absolute; bottom: 0;">
    <form action="/elevator/queryElevator" method="post">
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">使用证编号:</label>
                </div>
                <div class="weui-cell__hd weui-cell_primary">
                    <input class="weui-input" name="certificate" type="text" placeholder="请输入电梯使用证编号"/>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">单位地址:</label>
                </div>
                <div class="weui-cell__hd weui-cell_primary">
                    <input class="weui-input" name="addressOfUse" type="text" placeholder="请输入单位地址关键字"/>
                </div>
            </div>
        </div>

        <%--========================--%>
        <%--<div data-role="page" style="width: 30%; display: inline;">
            <div data-role="content">
                <div class="ui-field-contain">
                    <select name="select-native-1" id="querySelect" data-iconpos="left">
                        <option value="certificate">使用证编号</option>
                        <option value="address">单位地址</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="weui-cells" style="display:inline;width: 70%;">
            <div class="weui-cell">
                <div class="weui-cell__hd weui-cell_primary">
                    <input id="queryInput" class="weui-input" name="certificate" type="text" placeholder="请输入电梯使用证编号"/>
                </div>
            </div>
        </div>--%>
        <%--===========================--%>

        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" id="submitBtn" href="javascript:">提交</a>
        </div>
    </form>
</div>

<script>
    $(function () {

        // 选择查询方式
        /*$("#querySelect").change(function () {
            var val = $(this).val();
            if (val == "certificate") { // 按照使用证编号查询
                $("#queryInput").attr("placeholder", "请输入电梯使用证编号");
                $("#queryInput").attr("name", "certificate");
            } else if (val == "address") {
                $("#queryInput").attr("placeholder", "请输入单位地址关键字");
                $("#queryInput").attr("name", "addressOfUse");
            }
        });*/

        // 提交表单
        $("#submitBtn").click(function () {
            // 让用户只填写其中一种查询方式
            var certificate = $("input[name='certificate']").val();
            var address = $("input[name='addressOfUse']").val();

            if (certificate != null && certificate.trim() != "") { // 填写了使用编号
                if (address != null && address.trim() != "") { // 填写了单位地址
                    toastr.options = {
                        "timeOut": "2000",
                        "preventDuplicates": true,
                        "preventManyTimes": true,
                        "hideDuration": "1",
                        "positionClass": "toast-top-center"
                    };
                    toastr.info("只需填写其中一项");
                    return;
                }
            }

            $("form")[0].submit();
        });
    });
</script>

<%--<script>
    $(function () {
        $("#submitBtn").click(function () {
            // 获取数据，并转成JSON字符串
            var certificate = $("#textCertificate").val();
            var data = JSON.stringify({"certificate": certificate}); // JSON.stringify()方法将对象或数组转换为JSON字符串
        });
        $.ajax({
            url: "/elevator/findByCertificate",
            dataType: "JSON", // 预期服务器返回的数据类型
            type: "POST",     // 请求方式
            contentType: "application/json",  // 发送信息至服务器时的内容编码格式（不配置有什么影响？）
            // 发送到服务器的数据
            data: data,
            success:function (result) {

            }
        });
    });
</script>--%>
</body>
</html>

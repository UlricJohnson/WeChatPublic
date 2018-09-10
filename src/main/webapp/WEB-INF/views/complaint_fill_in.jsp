<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%-- 配置页面自适应 --%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>投诉电梯</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/toastr/toastr.min.css">
    <%--<script href="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>--%>
    <script src="<%=request.getContextPath()%>/js/jquery-1.12.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/toastr/toastr.min.js"></script>
</head>
<body>
<div style="width: 100%; height: 50%;">
    <%-- 电梯的图片 --%>
    <img src="<%=request.getContextPath()%>/img/headImg.png" alt="图片找不到了"
         style="display: block; margin: auto; padding-top: 80px;">
</div>

<div style="width: 100%; height: 50%; position: absolute; bottom: 0;">
    <form action="/complaint/toComplaint_fillIn" method="post">
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label for="certificate" class="weui-label">使用证编号：</label>
                </div>
                <div class="weui-cell__bd weui-cell_primary">
                    <input id="certificate" class="weui-input" name="certificate" type="text"
                           placeholder="请输入电梯使用证编号"/>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">单位地址：</label>
                </div>
                <div class="weui-cell__hd weui-cell_primary">
                    <input class="weui-input" name="addressOfUse" type="text" placeholder="请输入单位地址关键字"/>
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" id="submitBtn" href="javascript:void(0);">提交</a>
        </div>
    </form>
</div>

<script>
    $(function () {
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

</body>
</html>

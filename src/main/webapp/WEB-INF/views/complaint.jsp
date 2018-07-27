<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投诉</title>
    <link href="https://cdn.bootcss.com/weui/1.1.1/style/weui.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>
    <%--<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>--%>
    <script src="../../js/jweixin-1.2.0.js"></script>
</head>
<body>
<%--<div class="weui_cells_title">单选列表项</div>
<div class="weui_cell weui_cells_radio">
    <label class="weui_cell weui_check_label" for="x11">
        <div class="weui_cell_bd">
            <p style="color: red; font-weight: bold;">电梯关人，急需救援！</p>
        </div>
        <div class="weui_cell_ft">
            <input type="radio" class="weui_check" name="radio1" id="x11"/>
            <span class="weui_icon_checked"></span>
        </div>
    </label>
    <label class="weui_cell weui_check_label" for="x12">
        <div class="weui_cell_bd">
            <p>异常抖动</p>
        </div>
        <div class="weui_cell_ft">
            <input type="radio" name="radio1" class="weui_check" id="x12"/>
            <span class="weui_icon_checked"></span>
        </div>
    </label>
    <label class="weui_cell weui_check_label" for="x13">
        <div class="weui_cell_bd">
            <p>异常声响</p>
        </div>
        <div class="weui_cell_ft">
            <input type="radio" name="radio1" class="weui_check" id="x13"/>
            <span class="weui_icon_checked"></span>
        </div>
    </label>
    <label class="weui_cell weui_check_label" for="x14">
        <div class="weui_cell_bd">
            <p>电梯停运</p>
        </div>
        <div class="weui_cell_ft">
            <input type="radio" name="radio1" class="weui_check" id="x14"/>
            <span class="weui_icon_checked"></span>
        </div>
    </label>
    <label class="weui_cell weui_check_label" for="x15">
        <div class="weui_cell_bd">
            <p>其他</p>
        </div>
        <div class="weui_cell_ft">
            <input type="radio" name="radio1" class="weui_check" id="x15"/>
            <span class="weui_icon_checked"></span>
        </div>
    </label>
</div>--%>

<div>
    <div id="certificateDiv">
        <strong>电梯使用证编号：</strong>
        <span>${elevator.certificateOfUse}</span>
    </div>
    <div>
        <strong>设备地址：</strong>
        <span>${elevator.deviceAddress}</span>
    </div>
</div>
<div>
    <form action="/complaint/doComplaint" method="post">
        <input type="hidden" name="certificate" value="${elevator.certificateOfUse}">
        <label for="sketchSelect">电梯异常事项：</label>
        <select id="sketchSelect" name="sketch">
            <option value="电梯关人">电梯关人</option>
            <option value="异常抖动">异常抖动</option>
            <option value="异常声响">异常声响</option>
            <option value="电梯停运">电梯停运</option>
            <option value="其他">其他</option>
        </select>
        <label for="username">联系人：</label>
        <input id="username" type="text" name="username">
        <label for="contactNum">联系电话：</label>
        <input id="contactNum" type="text" name="contactNum">
        <label for="details">问题描述：</label>
        <input id="details" type="text" name="details">
        <input id="submitComplaint" type="submit" value="投诉">
        <%--<button id="submitComplaint">投诉</button>--%>
    </form>
</div>

<script>
    window.onload = function () {
        wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: 'wx7fdb9ee9b03c7239', // 必填，公众号的唯一标识
            timestamp: new Date().getTime(), // 必填，生成签名的时间戳
            nonceStr: '', // 必填，生成签名的随机串
            signature: '',// 必填，签名
            jsApiList: [] // 必填，需要使用的JS接口列表
        });
    }
    /*$(function () {
        $("#submitComplaint").click(function () {
            var sketchOption = $("#sketchSelect option:selected");
            var certificate = $("#certificateDiv span").text();
            var sketch = sketchOption.val();
            var username = $("#username").val();
            var contactNum = $("#contactNum").val();
            var details = $("#details").val();

            var data = JSON.stringify({
                "certificate": $("#certificateDiv").text(),
                "sketch": sketchOption.val(),
                "username": $("#username").val(),
                "contactNum": $("#contactNum").val(),
                "details": $("#details").val()
            });
            $.ajax({
                url: "/complaint/doComplaint",
                type: "POST",
                dataType: "JSON",
                data: data,
                success: function () {
                    window.location.href = "complaint_success.jsp?username=" + username + "&certificate=" + certificate;
                }
            });
        });
    });*/
</script>

</body>
</html>

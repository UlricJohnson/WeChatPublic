<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电梯信息列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.12.1.min.js"></script>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<c:forEach items="${paramMap.elevatorList}" var="elevator" varStatus="currIndex">
    <div style="float: left; margin: 10px; border: solid grey 1px;">
        <div style="display:inline; float: left;">
            <input class="weui-cells_radio" type="radio" name="radio" style="display: inline-block;"/>
        </div>
        <div style="display:inline; float: right;">
            <div style="display: inline-block;">
                <span>使用证编号：${elevator.certificateOfUse}</span>
            </div>
            <div style="display: inline-block;">
                <%--<span>设备地址：${elevator.deviceAddress}</span>--%>
                <span>设备地址：${elevator.addressOfUse}</span>
            </div>
        </div>
        <div style="display: none;" class="ui-collapsible-content ui-body-a formDiv">
            <form action="/elevator/checkEleMsg" method="post">
                <input type="hidden" value="${elevator.certificateOfUse}" name="certificateOfUse"/>
                <%--<input type="hidden" value="${elevator.deviceAddress}" name="deviceAddress"/>--%>
                <input type="hidden" value="${elevator.addressOfUse}" name="deviceAddress"/>
                <div class="weui-btn-area">
                    <a class="weui-btn weui-btn_plain-default complaintBtn" href="javascript:">信息无误，确认反馈</a>
                </div>
            </form>
        </div>
    </div>
</c:forEach>

<script>
    $(function () {
        $(".weui-cells_radio").each(function (index) {
            $(this).click(function () {
                var isChecked = $(this).is(":checked");
                if (isChecked) {
                    $($(".formDiv")[index]).css({
                        "display": "block"
                    });
                }/* else {
                    $($(".formDiv")[index]).css({
                        "display": "none"
                    });
                }*/

                $(".formDiv").each(function (formIndex) {
                    if (index != formIndex) {
                        $($(".formDiv")[formIndex]).css({
                            "display": "none"
                        });
                    }
                });
            });

        });

        // 确认电梯信息，提交到
        $(".complaintBtn").each(function (index) {
            $(this).click(function () {
                $("form")[index].submit();
            });
        });
    });
</script>

</body>
</html>

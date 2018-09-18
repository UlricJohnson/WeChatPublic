<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电梯信息列表</title>
</head>
<body>
<c:forEach items="${paramMap.elevatorList}" var="elevator" varStatus="currIndex">
    <div class="box">
        <label for="radio">选项一</label>
        <input class="radio" type="radio" id="radio" name="radio"/>
    </div>
    <div class="msg">
        <span>使用证编号：${elevator.certificateOfUse}</span>
        <span>设备地址：${elevator.deviceAddress}</span>
    </div>
    <div style="display: none;" class="ui-collapsible-content ui-body-a formDiv">
        <form action="/elevator/checkEleMsg" method="post">
            <input type="hidden" value="${elevator.certificateOfUse}" name="certificateOfUse"/>
            <input type="hidden" value="${elevator.deviceAddress}" name="deviceAddress"/>
            <div class="weui-btn-area">
                <a class="weui-btn weui-btn_warn complaintBtn" href="javascript:">信息无误，确认反馈</a>
            </div>
        </form>
    </div>
</c:forEach>

<script>
    $(function () {
        $(".radio").each(function (index) {
            var isChecked = $(this).is(":checked");
            if (isChecked) {
                $(".formDiv")[index].css({
                    "display": "block"
                });
            } else {
                $(".formDiv")[index].css({
                    "display": "none"
                });
            }
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

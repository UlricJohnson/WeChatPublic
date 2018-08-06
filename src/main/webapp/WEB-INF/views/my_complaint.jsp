<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>我的投诉记录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>
</head>
<body>

<div>
    <h2>用户：${loginUser.username}，您好</h2>
    <h4>您的投诉单如下：</h4>
</div>

<c:forEach items="${complaintList}" var="complaint" varStatus="currIndex">
    <p>投诉单#${currIndex.index + 1}：</p>
    <div>
        <span style="font-weight: bold">投诉简述：</span>
        <span>${complaint.sketch}</span>
    </div>
    <div>
        <span style="font-weight: bold">投诉详情：</span>
        <span>${complaint.details}</span>
    </div>

    <%-- 显示该投诉单相关电梯的使用证编号，点击跳到电梯信息页面 --%>
    <div>
        <c:if test="${not empty complaint.elevator}">
            <form action="/elevator/findByCertificate" method="post">
                <input name="certificate" type="hidden" value="${complaint.elevator.certificateOfUse}"/>
                <input type="submit" value="查看电梯信息">
            </form>

            <%--<script>
                displayElevatorInfo(${complaint.elevatorId},${complaint.elevator.id},${complaint.elevator.certificateOfUse})
            </script>--%>
        </c:if>
    </div>

    <%-- 如果这个投诉单上传了图片就显示 --%>
    <%--<div>
        &lt;%&ndash;<a href="javascript:void(0);" onclick="showImg(${complaint.imgUrl} + '')">显示图片</a>&ndash;%&gt;
        <button onclick="showImg(${complaint.imgUrl})">显示图片</button>
        <div id="imgDiv"></div>
    </div>--%>
    <%--<c:if test="${not empty complaint.imgUrl}">
        &lt;%&ndash; 获取投诉单的 imgUrl 属性（还是用分号;分割的） &ndash;%&gt;
        <c:set value="${complaint.imgUrl}" var="imgUrl"/>
        &lt;%&ndash; 把 imgUrl 属性切割成字符串数组 &ndash;%&gt;
        <c:set value="${fn:split(imgUrl, ';')}" var="imgUrls"/>
        &lt;%&ndash; 将图片逐个展示 &ndash;%&gt;
        <c:forEach items="${imgUrls}" var="img_url">
            <img src="${img_url}" alt="找不到图片"/>
        </c:forEach>
    </c:if>--%>
    <%-- 如果当前不是最后一项，就显示一个水平线 --%>
    <c:if test="${!currIndex.last}">
        <hr style="border: solid 1px black">
    </c:if>
</c:forEach>

<script>
    // 跳转到指定的电梯信息页面
    /*
        参数：
            complaintEleId：投诉单中的elevatorId
            eleId：电梯表的主键ID
            certificateOfUse：电梯使用证编号
     */
    /*function displayElevatorInfo(complaintEleId, eleId, certificateOfUse) {
        if(complaintEleId == eleId){
        }
    }*/

    // 显示投诉单的图片
    /*
        参数：imgUrl：图片路径（已用分号;拼接）
     */
    function showImg(imgUrl) {
        var imgs = [];
        imgs = imgUrl.split(";");

        // 显示图片
        for (var i = 0; i < imgs.length; i++) {
            $("#imgDiv").append(
                "<img src='" + imgs[i] + "' alt='找不到图片' style='width: 80px;height: 80px'/>");
        }
    }
</script>

</body>
</html>

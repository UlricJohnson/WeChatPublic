<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>我的投诉记录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/weui/example/example.css">--%>
    <script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>

    <%-- 使用 jquery-weui --%>
    <%--<!-- head 中 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.3/style/weui.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.1/css/jquery-weui.min.css">

    <!-- body 最后 -->
    <script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery-weui/1.2.1/js/jquery-weui.min.js"></script>

    <!-- 如果使用了某些拓展插件还需要额外的JS -->
    <script src="https://cdn.bootcss.com/jquery-weui/1.2.1/js/swiper.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery-weui/1.2.1/js/city-picker.min.js"></script>--%>

    <style>
        <%-- 模仿 .weui-btn_plain-default，因为在 input 标签上无法宽度100% --%>
        input[type='submit'] {
            width: 80%;
            background-color: white;
            border: 1px solid #353535;
            color: #353535;
            position: relative;
            display: block;
            margin-left: auto;
            margin-right: auto;
            padding-left: 14px;
            padding-right: 14px;
            box-sizing: border-box;
            font-size: 18px;
            text-align: center;
            text-decoration: none;
            /*line-height: ;*/
            border-radius: 5px;
            overflow: hidden;
        }
    </style>


</head>
<body>
<div class="weui-panel">
    <div class="weui-panel__hd" style="font-size: large;color: black;font-weight: bold;">
        <%--<h2 class="page__title">用户：${loginUser.username}，您好</h2>
        <p class="page__desc">您的投诉单如下：</p>--%>
        用户：${paramMap.eleUser.username}，您好
    </div>
    <c:choose>
        <c:when test="${empty paramMap.eleUser.complaintList}">
            <h3>您还没有投诉过电梯</h3>
        </c:when>
        <c:otherwise>
            <div class="weui-panel__bd">
                <c:forEach items="${paramMap.eleUser.complaintList}" var="complaint" varStatus="currIndex">
                    <div class="weui-cells__title">投诉单#${currIndex.index + 1}：</div>
                    <div class="weui-cells">
                        <div class="weui-cell">
                                <%--<div class="weui-cell__hd"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII=" alt="" style="width:20px;margin-right:5px;display:block"></div>--%>
                            <div class="weui-cell__bd">
                                <p>投诉简述：</p>
                            </div>
                            <div class="weui-cell__ft">${complaint.sketch}</div>
                        </div>
                        <div class="weui-cell">
                                <%--<div class="weui-cell__hd"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII=" alt="" style="width:20px;margin-right:5px;display:block"></div>--%>
                            <div class="weui-cell__bd">
                                <p>投诉详情：</p>
                            </div>
                            <div class="weui-cell__ft">${complaint.details}</div>
                        </div>
                            <%-- 显示该投诉单相关电梯的使用证编号，点击跳到电梯信息页面 --%>
                        <div class="weui-cell">
                            <c:if test="${not empty complaint.elevator}">
                                <form action="/elevator/findByCertificate" method="post"
                                      style="display:block; width: 100%">
                                    <input name="certificate" type="hidden"
                                           value="${complaint.elevator.certificateOfUse}"/>
                                    <input type="submit" value="查看电梯信息">
                                        <%--<a class="weui-btn weui-btn_plain-primary" href="javascript:;"
                                           onclick="checkEleInfo(this)">查看图片</a>--%>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>


</div>

<%-- ======================================== --%>
<%--
<div>
    <h2>用户：${loginUser.username}，您好</h2>
    <h4>您的投诉单如下：</h4>
</div>
--%>

<%--<c:forEach items="${complaintList}" var="complaint" varStatus="currIndex">
    <p>投诉单#${currIndex.index + 1}：</p>
    <div>
        <span style="font-weight: bold">投诉简述：</span>
        <span>${complaint.sketch}</span>
    </div>
    <div>
        <span style="font-weight: bold">投诉详情：</span>
        <span>${complaint.details}</span>
    </div>

    &lt;%&ndash; 显示该投诉单相关电梯的使用证编号，点击跳到电梯信息页面 &ndash;%&gt;
    <div>
        <c:if test="${not empty complaint.elevator}">
            <form action="/elevator/findByCertificate" method="post">
                <input name="certificate" type="hidden" value="${complaint.elevator.certificateOfUse}"/>
                <input type="submit" value="查看电梯信息">
            </form>

            &lt;%&ndash;<script>
                displayElevatorInfo(${complaint.elevatorId},${complaint.elevator.id},${complaint.elevator.certificateOfUse})
            </script>&ndash;%&gt;
        </c:if>
    </div>

    &lt;%&ndash; 如果这个投诉单上传了图片就显示 &ndash;%&gt;
    &lt;%&ndash;<div>
        &lt;%&ndash;<a href="javascript:void(0);" onclick="showImg(${complaint.imgUrl} + '')">显示图片</a>&ndash;%&gt;
        <button onclick="showImg(${complaint.imgUrl})">显示图片</button>
        <div id="imgDiv"></div>
    </div>&ndash;%&gt;
    &lt;%&ndash;<c:if test="${not empty complaint.imgUrl}">
        &lt;%&ndash; 获取投诉单的 imgUrl 属性（还是用分号;分割的） &ndash;%&gt;
        <c:set value="${complaint.imgUrl}" var="imgUrl"/>
        &lt;%&ndash; 把 imgUrl 属性切割成字符串数组 &ndash;%&gt;
        <c:set value="${fn:split(imgUrl, ';')}" var="imgUrls"/>
        &lt;%&ndash; 将图片逐个展示 &ndash;%&gt;
        <c:forEach items="${imgUrls}" var="img_url">
            <img src="${img_url}" alt="找不到图片"/>
        </c:forEach>
    </c:if>&ndash;%&gt;
    &lt;%&ndash; 如果当前不是最后一项，就显示一个水平线 &ndash;%&gt;
    <c:if test="${!currIndex.last}">
        <hr style="border: solid 1px black">
    </c:if>
</c:forEach>--%>

<script>
    $(function () {
        // 点击a按钮提交表单，自己作为参数
        function checkEleInfo(the) {
            alert("checkEleInfo()");
            var form = $(the).parentElement;
            $(form).submit();
        }
    });

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

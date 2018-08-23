<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>电梯信息</title>
    <%--<link href="https://cdn.bootcss.com/weui/1.1.0/style/weui.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>
    <style>
        .container {
            margin: 10px;
            padding: 0px;
        }

        .eleTable {
            margin: 10px;
            border: solid grey 1px;
        }

        .eleTable th {
            align: center;
            height: 30px;
            padding-top: 5px;
            padding-bottom: 5px;
            background-color: #E4ECF7;
        }

        .eleTable td {
            padding-top: 5px;
            padding-bottom: 5px;
            padding-left: 5px;
        }

        /* 表格的第1列 */
        .eleTable tr td:nth-child(1) {
            width: 40%;
            /*align: left;*/
        }
    </style>

</head>
<body>
<div class="container">
    <div class="table_container">
        <table class="eleTable" cellspacing="0" cellpadding="3" border="1">
            <thead>
            <th>项目</th>
            <th>数据</th>
            </thead>
            <tbody>
            <%--<tr>
                <td>序号</td>
                <td>${elevator.id}</td>
            </tr>
            <tr>
                <td>使用单位设备总数</td>
                <td>${elevator.totDevice}</td>
            </tr>--%>
            <tr>
                <td>设备详情</td>
                <td>${elevator.deviceDetails}</td>
            </tr>
            <tr>
                <td>使用单位名称</td>
                <td>${elevator.unitOfUse}</td>
            </tr>
            <tr>
                <td>使用单位地址</td>
                <td>${elevator.addressOfUse}</td>
            </tr>
            <tr>
                <td>联系人</td>
                <td>${elevator.contact}</td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td>${elevator.contactNumber}</td>
            </tr>
            <tr>
                <td>使用证编号</td>
                <%--<td id="certificateOfUse">${elevator.certificateOfUse}</td>--%>
                <td>${elevator.certificateOfUse}</td>
            </tr>
            <tr>
                <td>下次年检日期</td>
                <td>${elevator.nextYearlyInspection}</td>
            </tr>
            <tr>
                <td>设备型号</td>
                <td>${elevator.deviceModel}</td>
            </tr>
            <tr>
                <td>设备编号</td>
                <td>${elevator.deviceNumber}</td>
            </tr>
            <tr>
                <td>设备出厂编号</td>
                <td>${elevator.deviceFactoryNumber}</td>
            </tr>
            <tr>
                <td>设备注册号</td>
                <td>${elevator.deviceRegistrationNumber}</td>
            </tr>
            <tr>
                <td>设备地址</td>
                <%--<td id="deviceAddress">${elevator.deviceAddress}</td>--%>
                <td>${elevator.deviceAddress}</td>
            </tr>
            <tr>
                <td>所在镇街</td>
                <td>${elevator.town}</td>
            </tr>
            <tr>
                <td>使用单位部门地址</td>
                <td>${elevator.departmentAddress}</td>
            </tr>
            <tr>
                <td>设备类型</td>
                <td>${elevator.deviceType}</td>
            </tr>
            <tr>
                <td>设备状态</td>
                <td>${elevator.deviceState}</td>
            </tr>
            <tr>
                <td>制造单位</td>
                <td>${elevator.manufacturingUnit}</td>
            </tr>
            <tr>
                <td>安装单位</td>
                <td>${elevator.installationUnit}</td>
            </tr>
            <tr>
                <td>维保单位</td>
                <td>${elevator.maintenanceUnit}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <form action="/complaint/toComplaint_eleInfo" method="post">
    <%--<form action="/wechat/wclogin" method="post">--%>
        <%-- 使用证编号 --%>
        <input type="hidden" value="${elevator.certificateOfUse}" name="certificateOfUse">
        <%-- 设备地址 --%>
        <input type="hidden" value="${elevator.deviceAddress}" name="deviceAddress">
        <%-- 网页授权后的回调地址 --%>
        <%--<input type="hidden" value="/complaint/toComplaint_eleInfo" name="redirectUri">--%>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_warn" id="complaintBtn" href="javascript:">投诉</a>
        </div>
        <%--<input type="submit" value="投诉">--%>
    </form>
</div>

<script>

    $(function () {
        $("#complaintBtn").click(function () {
            $("form")[0].submit();
        });
    });

    /*$(function () {
        $("#complaintBtn").click(function () {
            // 获取数据，并通过 JSON.stringify() 方法将对象或数组转换为JSON字符串
            var data = JSON.stringify({
                "id": $("#elevatorId").text(),  // 电梯ID
                "certificateOfUse": $("#certificateOfUse").text(),  // 使用证编号
                "deviceAddress": $("#deviceAddress").text() // 设备地址
            });

            $.ajax({
                url: "/complaint/toComplaint_eleInfo",
                dataType: "JSON", // 预期服务器返回的数据类型
                type: "POST",     // 请求方式
                asyn: false,    // 取消异步方式
                contentType: "application/json",  // 发送信息至服务器时的内容编码格式（不配置有什么影响？）
                // 发送到服务器的数据
                data: data,
                success: function () {
                    alert("success");
                }
            });
        });
    });*/
</script>

</body>
</html>

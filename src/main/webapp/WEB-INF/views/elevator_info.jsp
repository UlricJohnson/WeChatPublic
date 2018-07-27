<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电梯信息</title>
    <%--<link rel="stylesheet" href="/weui/style/weui.css">--%>
    <link href="https://cdn.bootcss.com/weui/1.1.0/style/weui.css" rel="stylesheet">
    <%-- <style type="text/css">
         .container1 {
             width: 94%;
             height: 96%;
             background-color: rgb(248, 248, 248);
             margin: 10px auto;
             overflow: hidden;
         }

         .container2 {
             width: 94%;
             height: 96%;
             background-color: #ffffff;
             border: 1px solid rgb(238, 238, 238);
             margin: 10px auto;
             border-radius: 5px;
             overflow: hidden;
         }

         .table_css {
             width: 95%;
             margin: 3px auto;
         }

         table td, th {
             padding: 10px;
             font-size: 16px;
         }

         .zhanghu {
             background-color: rgb(255, 255, 255);
             /*border-left: 3px solid rgb(217,217,217);*/
             color: #1AAD19;
         }

         .biaotou {
             background-color: rgb(255, 255, 255);
             border-bottom: 2px solid rgb(217, 217, 217);
             color: #1AAD19;
         }

         .text1 {
             background-color: rgb(255, 255, 255);
             border-bottom: 1px solid rgb(217, 217, 217);
             color: #1AAD19;
         }

         .text2 {
             background-color: rgb(248, 248, 248);
             border-bottom: 1px solid rgb(217, 217, 217);
             color: #1AAD19;
         }
     </style>--%>
    <script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>
</head>
<body>
<div class="container1">
    <div class="container2">
        <%--<table class="table_css">
            <tr>
                <td class="zhanghu">登陆账号 : <span id="UserName">admin</span></td>
            </tr>
            <tr>
                <td class="zhanghu">用户姓名 : <span id="UserCode">菜鸟的自我挣扎</span></td>
            </tr>
        </table>--%>

        <table class="table_css" cellspacing="0" border="1px">
            <thead>
            <th align="left" class="biaotou">项目</th>
            <th align="left" class="biaotou">数据</th>
            </thead>
            <tbody>
            <tr>
                <td width="50%" class="text1">序号</td>
                <td id="elevatorId" width="50%" align="left" class="text1">${requestScope.elevator.id}</td>
            </tr>
            <tr>
                <td class="text2">使用单位设备总数</td>
                <td align="left" class="text2">${requestScope.elevator.totDevice}</td>
            </tr>
            <tr>
                <td class="text2">使用单位名称</td>
                <td align="left" class="text1">${requestScope.elevator.unitOfUse}</td>
            </tr>
            <tr>
                <td class="text1">使用单位地址</td>
                <td align="left" class="text2">${elevator.addressOfUse}</td>
            </tr>
            <tr>
                <td class="text1">联系人</td>
                <td align="left" class="text1">${elevator.contact}</td>
            </tr>
            <tr>
                <td class="text2">联系电话</td>
                <td align="left" class="text2">${elevator.contactNumber}</td>
            </tr>
            <tr>
                <td class="text1">使用证编号</td>
                <td id="certificateOfUse" align="left" class="text1">${elevator.certificateOfUse}</td>
            </tr>
            <tr>
                <td class="text2">下次年检日期</td>
                <td align="left" class="text2">${elevator.nextYearlyInspection}</td>
            </tr>
            <tr>
                <td class="text1">设备型号</td>
                <td align="left" class="text1">${elevator.deviceModel}</td>
            </tr>
            <tr>
                <td class="text2">设备编号</td>
                <td align="left" class="text2">${elevator.deviceNumber}</td>
            </tr>
            <tr>
                <td class="text1">设备出厂编号</td>
                <td align="left" class="text1">${elevator.deviceFactoryNumber}</td>
            </tr>
            <tr>
                <td class="text2">设备注册号</td>
                <td align="left" class="text2">${elevator.deviceRegistrationNumber}</td>
            </tr>
            <tr>
                <td class="text1">设备地址</td>
                <td id="deviceAddress" align="left" class="text1">${elevator.deviceAddress}</td>
            </tr>
            <tr>
                <td class="text2">所在镇街</td>
                <td align="left" class="text2">${elevator.town}</td>
            </tr>
            <tr>
                <td class="text1">使用单位部门地址</td>
                <td align="left" class="text1">${elevator.departmentAddress}</td>
            </tr>
            <tr>
                <td class="text2">设备类型</td>
                <td align="left" class="text2">${elevator.deviceType}</td>
            </tr>
            <tr>
                <td class="text1">设备状态</td>
                <td align="left" class="text1">${elevator.deviceState}</td>
            </tr>
            <tr>
                <td class="text2">制造单位</td>
                <td align="left" class="text2">${elevator.manufacturingUnit}</td>
            </tr>
            <tr>
                <td class="text1">安装单位</td>
                <td align="left" class="text1">${elevator.installationUnit}</td>
            </tr>
            <tr>
                <td class="text2">维保单位</td>
                <td align="left" class="text2">${elevator.maintenanceUnit}</td>
            </tr>
            <tr>
                <td class="text1">设备详情</td>
                <td align="left" class="text1">${elevator.deviceDetails}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <%--<a href="/complaint?${elevator.certificateOfUse}"--%>
    <%--class="weui_btn weui_btn_warn">投诉complaint</a>--%>
    <%--<input id="complaintBtn" class="weui_btn_warn" type="button" value="投诉">--%>

    <form action="/complaint/toComplaint_eleInfo" method="post">
        <%-- 使用证编号 --%>
        <input type="hidden" value="${elevator.certificateOfUse}" name="certificateOfUse">
        <%-- 设备地址 --%>
        <input type="hidden" value="${elevator.deviceAddress}" name="deviceAddress">
            <input type="submit" value="投诉">
    </form>
</div>

<script>
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

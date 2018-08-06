<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%-- 配置页面自适应 --%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>电梯信息</title>
    <%--<link href="https://cdn.bootcss.com/weui/1.1.0/style/weui.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
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
<div>
    <div>
        <table cellspacing="0" border="1px">
            <thead>
            <th align="left">项目</th>
            <th align="left">数据</th>
            </thead>
            <tbody>
            <tr>
                <td width="50%">序号</td>
                <td id="elevatorId" width="50%" align="left">${requestScope.elevator.id}</td>
            </tr>
            <tr>
                <td>使用单位设备总数</td>
                <td align="left">${requestScope.elevator.totDevice}</td>
            </tr>
            <tr>
                <td>使用单位名称</td>
                <td align="left">${requestScope.elevator.unitOfUse}</td>
            </tr>
            <tr>
                <td>使用单位地址</td>
                <td align="left">${elevator.addressOfUse}</td>
            </tr>
            <tr>
                <td>联系人</td>
                <td align="left">${elevator.contact}</td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td align="left">${elevator.contactNumber}</td>
            </tr>
            <tr>
                <td>使用证编号</td>
                <td id="certificateOfUse" align="left">${elevator.certificateOfUse}</td>
            </tr>
            <tr>
                <td>下次年检日期</td>
                <td align="left">${elevator.nextYearlyInspection}</td>
            </tr>
            <tr>
                <td>设备型号</td>
                <td align="left">${elevator.deviceModel}</td>
            </tr>
            <tr>
                <td>设备编号</td>
                <td align="left">${elevator.deviceNumber}</td>
            </tr>
            <tr>
                <td>设备出厂编号</td>
                <td align="left">${elevator.deviceFactoryNumber}</td>
            </tr>
            <tr>
                <td>设备注册号</td>
                <td align="left">${elevator.deviceRegistrationNumber}</td>
            </tr>
            <tr>
                <td></td>
                设备地址</td>
                <td id="deviceAddress" align="left">${elevator.deviceAddress}</td>
            </tr>
            <tr>
                <td>所在镇街</td>
                <td align="left">${elevator.town}</td>
            </tr>
            <tr>
                <td>使用单位部门地址</td>
                <td align="left">${elevator.departmentAddress}</td>
            </tr>
            <tr>
                <td>设备类型</td>
                <td align="left">${elevator.deviceType}</td>
            </tr>
            <tr>
                <td>设备状态</td>
                <td align="left">${elevator.deviceState}</td>
            </tr>
            <tr>
                <td>制造单位</td>
                <td align="left">${elevator.manufacturingUnit}</td>
            </tr>
            <tr>
                <td>安装单位</td>
                <td align="left">${elevator.installationUnit}</td>
            </tr>
            <tr>
                <td>维保单位</td>
                <td align="left">${elevator.maintenanceUnit}</td>
            </tr>
            <tr>
                <td>设备详情</td>
                <td align="left">${elevator.deviceDetails}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <form action="/complaint/toComplaint_eleInfo" method="post">
        <%-- 使用证编号 --%>
        <input type="hidden" value="${elevator.certificateOfUse}" name="certificateOfUse">
        <%-- 设备地址 --%>
        <input type="hidden" value="${elevator.deviceAddress}" name="deviceAddress">
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

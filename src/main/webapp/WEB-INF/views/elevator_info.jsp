<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>电梯信息</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <%--<link href="https://cdn.bootcss.com/jquery-mobile/1.4.4/jquery.mobile.css" rel="stylesheet">--%>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/jquery-mobile/jquery.mobile-1.4.5.min.css"/>
    <script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/jquery-mobile/jquery.mobile-1.4.5.min.js"></script>
    <style>
        .eleTable {
            /*margin: 10px;*/
            border: solid grey 1px;
        }

        .eleTable th {
            text-align: center;
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
            width: 35%;
        }
    </style>

</head>
<body>
<%--<div class="container">
    <div class="table_container">
        <table class="eleTable" cellspacing="0" cellpadding="3" border="1">
            <thead>
            <th>项目</th>
            <th>数据</th>
            </thead>
            <tbody>
            &lt;%&ndash;<tr>
                <td>序号</td>
                <td>${elevator.id}</td>
            </tr>
            <tr>
                <td>使用单位设备总数</td>
                <td>${elevator.totDevice}</td>
            </tr>&ndash;%&gt;
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
                &lt;%&ndash;<td id="certificateOfUse">${elevator.certificateOfUse}</td>&ndash;%&gt;
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
                &lt;%&ndash;<td id="deviceAddress">${elevator.deviceAddress}</td>&ndash;%&gt;
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
        &lt;%&ndash; 使用证编号 &ndash;%&gt;
        <input type="hidden" value="${elevator.certificateOfUse}" name="certificateOfUse">
        &lt;%&ndash; 设备地址 &ndash;%&gt;
        <input type="hidden" value="${elevator.deviceAddress}" name="deviceAddress">
        &lt;%&ndash; 网页授权后的回调地址 &ndash;%&gt;
        &lt;%&ndash;<input type="hidden" value="/complaint/toComplaint_eleInfo" name="redirectUri">&ndash;%&gt;
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_warn" id="complaintBtn" href="javascript:">投诉</a>
        </div>
        &lt;%&ndash;<input type="submit" value="投诉">&ndash;%&gt;
    </form>
</div>--%>

<c:forEach items="${paramMap.elevatorList}" var="elevator" varStatus="currIndex">
    <div class="ui-collapsible ui-collapsible-inset ui-corner-all ui-collapsible-themed-content ui-first-child ui-collapsible-collapsed">
        <h1 class="my-collapse ui-collapsible-heading ui-collapsible-heading-collapsed">
            <a href="#" class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-b ui-icon-carat-l">
                    ${elevator.addressOfUse}
            </a>
        </h1>
        <div style="display: none;" class="ui-collapsible-content ui-body-a">
            <!--<table class="table-stripe" cellspacing="0" cellpadding="3" border="1">-->
                <%--<table class="table-stripe eleTable" style="width: 100%;" data-role="table" data-column-btn-theme="b"
                       data-column-popup-theme="a" data-mode="columntoggle">--%>
            <table class="table-stripe eleTable" style="width: 100%;">
                <thead>
                <th>项目</th>
                <th>数据</th>
                </thead>
                <tbody>
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
            <form action="/complaint/toComplaint_eleInfo" method="post">
                <input type="hidden" value="${elevator.certificateOfUse}" name="certificateOfUse">
                <input type="hidden" value="${elevator.deviceAddress}" name="deviceAddress">
                <div class="weui-btn-area">
                    <a class="weui-btn weui-btn_warn complaintBtn" href="javascript:">投诉</a>
                </div>
            </form>
        </div>
    </div>
</c:forEach>
<script>
    $(function () {
        // 展开/收起
        $(".my-collapse").each(function () {
            $(this).click(function () {
                var $this = $(this); // 不知道为什么一定要在这里声明一个变量，不能在下面直接使用$(this)
                var $collapseDiv = $this.siblings("div");
                if ($collapseDiv.is(":visible")) {
                    $collapseDiv.hide();
                    $this.children("a").first().removeClass("ui-icon-carat-d").addClass("ui-icon-carat-l");
                } else {
                    $collapseDiv.show();
                    $this.children("a").first().removeClass("ui-icon-carat-l").addClass("ui-icon-carat-d");
                }
            });
        });

        // 提交投诉表单
        $(".complaintBtn").each(function (index) {
            $(this).click(function () {
                $("form")[index].submit();
            });
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

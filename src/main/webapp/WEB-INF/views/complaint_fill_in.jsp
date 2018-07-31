<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投诉电梯</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
</head>
<body>
<div>
    <form action="/complaint/toComplaint_fillIn" method="post">
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell">
                <div class="weui_cell_hd">
                    <label for="certificate" class="weui_label">请输入您要投诉的电梯使用证编号：</label>
                </div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input id="certificate" class="weui_input" name="certificateOfUse" type="text"
                           placeholder="请在此输入电梯使用证编号"/>
                </div>
            </div>
        </div>
        <div class="weui_btn_area">
            <a class="weui_btn weui_btn_primary" id="submitBtn" href="javascript:">提交</a>
        </div>
        <%--<label for="certificate">请输入您要投诉的电梯使用证编号：</label>--%>
        <%--<input id="certificate" type="text" name="certificateOfUse">--%>
        <%--<input type="submit" value="提交">--%>
    </form>
</div>

<script>
    $(function () {
        $("#submitBtn").click(function () {
            $("form")[0].submit();
        });
    });
</script>

</body>
</html>

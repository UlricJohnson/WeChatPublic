<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>投诉</title>
    <%--<link href="https://cdn.bootcss.com/weui/1.1.1/style/weui.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/weui/style/weui.css">
    <%--<script src="https://cdn.bootcss.com/jquery/1.12.1/jquery.min.js"></script>--%>
    <script src="<%=request.getContextPath()%>/js/jquery-1.12.1.min.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
</head>
<body>

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
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <span style="color: red;">*</span>
                <div class="weui-cell__hd">
                    <label class="weui-label">电梯异常事项：</label>
                </div>
                <div class="weui-cell__bd weui-cell_primary">
                    <%--<input class="weui-input" name="name" type="text" placeholder="请在此输入姓名" />--%>
                    <select id="sketchSelect" name="sketch">
                        <option value="">--请选择--</option>
                        <option value="电梯关人">电梯关人</option>
                        <option value="异常抖动">异常抖动</option>
                        <option value="异常声响">异常声响</option>
                        <option value="电梯停运">电梯停运</option>
                        <option value="其他">其他</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="weui-cell">
            <span style="color: red;">*</span>
            <div class="weui-cell__hd">
                <label for="username">联系人：</label>
            </div>
            <div class="weui-cell__bd weui-cell_primary">
                <input id="username" class="weui-input" name="username" type="text" placeholder="请在此输入您的姓名"/>
            </div>
        </div>
        <div class="weui-cell">
            <span style="color: red;">*</span>
            <div class="weui-cell__hd">
                <label for="contactNum">联系电话：</label>
            </div>
            <div class="weui-cell__bd weui-cell_primary">
                <input id="contactNum" class="weui-input" name="contactNum" type="text" placeholder="请在此输入您的联系方式"/>
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="details">问题描述：</label>
            </div>
            <div class="weui-cell__bd weui-cell_primary">
                <%--<input id="details" class="weui-input" name="details" type="text" />--%>
                <textarea name="details" id="details" cols="30" rows="10"></textarea>
            </div>
        </div>
        <input id="imgUrl" type="hidden" name="imgUrl">
        <%--<input id="submitComplaint" type="submit" value="投诉">--%>
        <div class="weui-btn-area">
            <a id="uploadImageBtn" class="weui-btn weui-btn_plain-primary" href="javascript:void(0);">上传图片</a>
            <%--<span style="width: 50px;"></span>--%>
            <br><br>
            <a class="weui-btn weui-btn_warn" id="submitComplaint" href="javascript:">投诉</a>
        </div>
    </form>
    <div id="picDiv"></div>

</div>

<script>
    $(function () {

        // 点击 “投诉” 按钮提交表单
        $("#submitComplaint").click(function () {
            // 检查必填项：异常实现、联系人姓名、联系方式
            var sketch = $("#sketchSelect option:selected").val();
            var username = $("#username").val();
            var contactNum = $("#contactNum").val();

            if (sketch == null || sketch == ""){
                alert("请选择异常事项");
                $("#sketchSelect").focus();
                return;
            }
            if (username == null || username == "") {
                alert("请填写联系人姓名");
                $("#username").focus();
                return;
            }
            if (contactNum == null || contactNum == "") {
                alert("请填写联系方式");
                $("#contactNum").focus();
                return;
            }
            $("form")[0].submit();
        });

        // 用来存放图片超链接imgUrl
        var imgUrl = ""; // 有多个超链接用分号;隔开

        // 点击 “上传图片” 按钮之后
        $("#uploadImageBtn").click(function () {
            var jsApiList = ['chooseImage', 'uploadImage', 'downloadImage'];

            // 发送 AJAX 请求向后台获取配置JS-SDK的参数，并进行配置
            $.ajax({
                url: "/wechat/configJsSdk",
                type: "POST",
                dataType: "JSON",
                data: {"url": window.location.href}, // 需要调用 JS-SDK 的页面的 URL 作为参数
                success: function (result) {
                    var appId = result.appId;
                    var timestamp = result.timestamp;
                    var noncestr = result.noncestr;
                    var signature = result.signature;

                    // alert("开始wx.config()");
                    wx.config({
                        // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                        "debug": true,    // 发布前记得改为 false ！！！！
                        "appId": appId, // 必填，公众号的唯一标识
                        "timestamp": timestamp, // 必填，生成签名的时间戳
                        "nonceStr": noncestr, // 必填，生成签名的随机串
                        "signature": signature,// 必填，签名，见附录1
                        // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                        "jsApiList": jsApiList
                    });
                }
            });

            /*
            config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后。
            config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。
            对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
            */
            wx.ready(function () {
                wx.checkJsApi({
                    jsApiList: jsApiList,
                    success: function (result) {
                        // alert("wx.checkJsApi()：success，返回数据：\n#checkResult: " + result.checkResult + "\n#errMsg: " + result.errMsg);
                        if (result.checkResult.getLocation == false) {
                            alert("您的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！");
                            return;
                        } else {
                            wxChooseImage();
                        }
                    }
                });
            });
            wx.error(function (result) {
                alert("wx.config()验证失败，请重试！");
                wx.closeWindow();
            });
        });

        function wxChooseImage() {
            //
            var images = {
                localId: [],
                serverId: []
            };

            var currUpload = -1; // 当前正在上传的图片的 localId 的序号（images 中）

            // 执行微信调用JS接口选择相册图片
            wx.chooseImage({
                sourceType: ["album", "camera"], // 指定来源：相册，相机
                success: function (result) {
                    // alert("wx.chooseImage：success");
                    images.localId = result.localIds;
                    // alert("已选择 " + result.localIds.length + " 张图片");

                    if (images.localId.length == 0) {
                        alert("请先使用 chooseImage 接口选择图片");
                        return;
                    }

                    // 显示图片
                    for (var i = 0; i < images.localId.length; i++) {
                        $("#picDiv").append("<img style='width: 80px; height: 80px' src='" + images.localId[i] + "'/>");
                    }

                    // 设置延迟100毫秒执行上传操作
                    setTimeout(wxUploadImage, 100);
                }
            });

            function wxUploadImage() {
                currUpload++;
                wx.uploadImage({
                    localId: images.localId[currUpload].toString(), // 需要上传的图片的本地ID，由 chooseImage 接口获得
                    success: function (result) {
                        var mediaId = result.serverId; // 返回图片的服务器端ID，即 mediaId
//                        alert("wx.uploadImage返回的serverId(mediaId)：" + mediaId);
//                         alert("wx.uploadImage():success");

                        // 将获取到的 mediaId 传入后台，进行图片的保存
                        $.ajax({
                            url: "/complaint/saveImage",
                            type: "post",
                            data: {mediaId: mediaId},
                            dataType: "json",
                            success: function (result) {
                                if (result.success = true) {
                                    alert("上传成功：" + ((currUpload + 1) + "/" + images.localId.length));
                                    imgUrl += result.imgUrl + ";";
                                    if (currUpload + 1 < images.localId.length) {
                                        wxUploadImage();
                                    } else {
                                        $("#imgUrl").val(imgUrl);
                                    }
                                } else {
                                    alert("上传失败：" + result.msg);
                                }
                            }
                        });
                    },
                    fail: function (result) {
                        alert("上传图片失败，请重试");
                        $("#picDiv").html("");
                    }
                });
            }
        }
    });

</script>

</body>
</html>

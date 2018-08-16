package com.casaba.entity;

/**
 * 微信用户
 * <p>
 * 获取用户信息时返回的JSON 数据如下：
 * {
 * "openid":" OPENID",
 * " nickname": NICKNAME,
 * "sex":"1",
 * "province":"PROVINCE"
 * "city":"CITY",
 * "country":"COUNTRY",
 * "headimgurl": "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
 * "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
 * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
 * }
 * created by Ulric on 2018/7/16
 */

public class WeChatUser {
    private Long id;    // 主键ID

    private String openId;// 微信用户的OpenID

    private String nickname; // 用户昵称

    private Integer sex; // 性别，值为1时是男性，值为2时是女性，值为0时是未知

    private String province; // 用户个人资料填写的省份

    private String city; // 用户个人资料填写的城市

    private String country; // 国家，如中国为CN

    private String headImgUrl; // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。详见上例。

    private String privilege; // 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom），用分号;拼接。

    private String unionId; // 不知道什么用。公众号开发文档上说：只有在用户将公众号绑定到微信开放平台账号后，才会出现该字段。

    private User user; // 绑定的电梯用户

    /*=========================*/

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getOpenId() { return openId; }

    public void setOpenId(String openId) { this.openId = openId; }

    public Integer getSex() { return sex; }

    public void setSex(Integer sex) { this.sex = sex; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getProvince() { return province; }

    public void setProvince(String province) { this.province = province; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getHeadImgUrl() { return headImgUrl; }

    public void setHeadImgUrl(String headImgUrl) { this.headImgUrl = headImgUrl; }

    public String getPrivilege() { return privilege; }

    public void setPrivilege(String privilege) { this.privilege = privilege; }

    public String getUnionId() { return unionId; }

    public void setUnionId(String unionId) { this.unionId = unionId; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "WeChatUser{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", privilege='" + privilege + '\'' +
                ", unionId='" + unionId + '\'' +
                ", user=" + user +
                '}';
    }
}
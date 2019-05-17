package com.northmeter.smartenergyregulation.bean;

/**
 * Created by dyd on 2019/5/13.
 * 用户登录
 */

public class LoginResponse extends CommonResponse{
    private int expire;//过期时间
    private String imageurl;//Logo地址
    private String userName;//用户名称
    private long userId;//用户ID
    private String customerName;//商户名称
    private String customerId;//商户ID
    private String token;//令牌

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

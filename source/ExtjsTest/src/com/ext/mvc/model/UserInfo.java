package com.ext.mvc.model;

import java.io.Serializable;
/**
 * 
 * @author WangYG
 *  @description : UserInfo 实体类-数据模型
 */
public class UserInfo implements Serializable{
    
    private static final long serialVersionUID = 207790648084814472L;
    private String username;//用户名
    private int age;//年龄
    private String phone;//电话
    private String email;//邮箱
    private String likes;//爱好
    private int faceStatus;//政治面貌
    private String address;//地址
    private String remark;//备注
    
    public UserInfo(){}
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLikes() {
        return likes;
    }
    public void setLikes(String likes) {
        this.likes = likes;
    }
    public int getFaceStatus() {
        return faceStatus;
    }
    public void setFaceStatus(int faceStatus) {
        this.faceStatus = faceStatus;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    

    @Override
    public String toString() {
        return "UserInfo [username=" + username + ", age=" + age + ", phone="
                + phone + ", email=" + email + ", likes=" + likes
                + ", faceStatus=" + faceStatus + ", address=" + address
                + ", remark=" + remark + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserInfo other = (UserInfo) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
    
    
    
}

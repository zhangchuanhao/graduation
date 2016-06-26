package com.just.pro.bean;

import java.io.Serializable;

public class User implements Serializable{
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;
    //学号
    private String id;
    //姓名
    private String name;
    //密码
    private String password;
    //课题号id
    private String proId;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", proId=" + proId + "]";
	}
    
}

package com.just.pro.bean;

import java.io.Serializable;
/**
 * 交互结果
 * @author zhangch
 *
 */
public class NoteResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//状态
	private int status;
	//消息
	private String msg;
	//数据
	private Object data;
	//页数
	private Integer pages;
	//姓名
	private String name;
	//专业
	private String dept;
	
	
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "NoteResult [status=" + status + ", msg=" + msg + ", data="
				+ data + "]";
	}
}

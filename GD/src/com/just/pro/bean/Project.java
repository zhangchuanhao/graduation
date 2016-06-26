package com.just.pro.bean;

import java.io.Serializable;

/**
 * 课题
 * @author zhangch
 *
 */
public class Project implements Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	//课题id
	private String pro_id;
	//课题名称
	private String pro_title;
	//课题描述
	private String pro_desc;
	//选中状态:0未选中，1选中
	private String pro_status;
	//指导老师
	private String pro_teacher;
	
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getPro_title() {
		return pro_title;
	}
	public void setPro_title(String pro_title) {
		this.pro_title = pro_title;
	}
	public String getPro_desc() {
		return pro_desc;
	}
	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}
	public String getPro_status() {
		return pro_status;
	}
	public void setPro_status(String pro_status) {
		this.pro_status = pro_status;
	}
	public String getPro_teacher() {
		return pro_teacher;
	}
	public void setPro_teacher(String pro_teacher) {
		this.pro_teacher = pro_teacher;
	}
	@Override
	public String toString() {
		return "Project [pro_id=" + pro_id + ", pro_title=" + pro_title
				+ ", pro_desc=" + pro_desc + ", pro_status=" + pro_status + "]";
	}
}

package com.just.pro.bean;

import java.io.Serializable;
/**
 * 前后台交互的数据
 * @author zhangch
 *
 */
public class DataFromWeb implements Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 关键字
	 */
	private String keywords;
	/**
	 *  当前页码
	 */
	private Integer page;
	/**
	 *  每页显示的条数
	 */
	private Integer number;
	/**
	 *  开始的条数
	 */
	private Integer beginNumber;
	/**
	 * 总页数
	 */
	private Integer totalpages;
	
	
	

	public Integer getBeginNumber() {
		return beginNumber;
	}

	public void setBeginNumber(Integer beginNumber) {
		this.beginNumber = beginNumber;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "DataFromWeb [keywords=" + keywords + ", page=" + page
				+ ", number=" + number + ", beginId=" + beginNumber + "]";
	}

	public Integer getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(Integer totalpages) {
		this.totalpages = totalpages;
	}
	
	
}

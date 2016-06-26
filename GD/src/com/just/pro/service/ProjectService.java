package com.just.pro.service;

import com.just.pro.bean.DataFromWeb;

public interface ProjectService {
	/**
	 * 处理页码，关键字
	 */
	public DataFromWeb doData(Integer page,String keywords);
}

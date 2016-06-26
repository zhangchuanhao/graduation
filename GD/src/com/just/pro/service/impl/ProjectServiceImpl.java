package com.just.pro.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.just.pro.bean.DataFromWeb;
import com.just.pro.dao.ProjectsMapperDao;
import com.just.pro.service.ProjectService;
@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
	@Resource
	private ProjectsMapperDao projectsMapperDao;
	
	public DataFromWeb doData(Integer page,String keywords){
		DataFromWeb dfw = new DataFromWeb();
		dfw.setKeywords(keywords);
		//符合条件的条数
		Integer n = projectsMapperDao.findKeywordsNumber(dfw);
		// 处理数据
		keywords = keywords == null ? "" : keywords;
		page = page == null ? 1 : page;
		// 总页数
		int pages = (int) Math.ceil(n/20.0);
		// 处理页数显示的数据条数,每页显示20条
		int begin = (page-1)*20;
		// 设置查询的数据
		dfw.setTotalpages(pages);
		dfw.setPage(page);
		dfw.setBeginNumber(begin);
		return dfw;
	}
}

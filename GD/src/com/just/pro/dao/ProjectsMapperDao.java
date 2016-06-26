package com.just.pro.dao;

import java.util.List;

import com.just.pro.bean.DataFromWeb;
import com.just.pro.bean.Project;

public interface ProjectsMapperDao {
	/**
	 * 加载数据
	 * @param dfw 数据bean
	 * @return
	 */
	public List<Project> loadAllProjects(DataFromWeb dfw);
	/**
	 * 加载查询的项目
	 * @param keyword
	 * @return
	 */
	public List<Project> loadSearchProjects(DataFromWeb dfw);
	/**
	 * 根据关键字查询符合条件数据的条数
	 * @param str关键字
	 * @return
	 */
	public int findKeywordsNumber(DataFromWeb dfw);
	/**
	 * 更新课题表选中状态
	 * @param selectedId 更新值的id
	 * @return
	 */
	public void updateProject(Integer selectedId);
	/**
	 * 根据课题id删除课题
	 * @param id
	 */
	public void deleteProject(Integer id);
}

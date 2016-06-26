package com.just.pro.contorller.project;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.just.pro.bean.DataFromWeb;
import com.just.pro.bean.NoteResult;
import com.just.pro.bean.Project;
import com.just.pro.dao.ProjectsMapperDao;
import com.just.pro.service.ProjectService;

/**
 * x
 * 
 * @author zhangch
 * 
 */
@Controller
public class ProjectShowController {
	@Resource
	private ProjectsMapperDao pmDao;
	@Resource
	private ProjectService proService;

	/**
	 * 根据页码与关键字查询数据，默认为第一页关键字为null
	 * @param page
	 * @param keywords
	 * @return
	 */
	@RequestMapping("/projects.do")
	@ResponseBody
	// 将返回值编程json输出
	public NoteResult execute(Integer page, String keywords) {
		// 设置查询的数据
		DataFromWeb dfw = proService.doData(page, keywords);
		// 返回结果设置
		NoteResult result = new NoteResult();
		// 数据集合
		List<Project> pros = new ArrayList<Project>();
		// 加载所有项目
		pros = pmDao.loadAllProjects(dfw);
		result.setPages(dfw.getTotalpages());
		result.setData(pros);
		result.setMsg("Done");
		result.setStatus(0);
		return result;
	}
	@RequestMapping("/submit.do")
	@ResponseBody
	public NoteResult exeSubmit(Integer userId,Integer selectedId){
		NoteResult result = new NoteResult();
		pmDao.updateProject(selectedId);
		result.setMsg("提交成功");
		result.setStatus(0);
		return result;
		
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public NoteResult exeDelete(Integer id){
		NoteResult result = new NoteResult();
		pmDao.deleteProject(id);
		result.setMsg("删除成功");
		result.setStatus(0);
		return result;
	}
}

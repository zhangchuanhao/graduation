package com.just.pro.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.just.pro.bean.NoteResult;
import com.just.pro.bean.User;
import com.just.pro.dao.UserMapperDao;
import com.just.pro.service.UserService;

@Service("userService")//扫描
public class UserServiceImpl implements UserService{
	@Resource//注入
	private UserMapperDao userDao;
	
	public NoteResult checkLogin(String name, String password) {
		NoteResult result = new NoteResult();
		User user = userDao.findById(name);
		if(user==null){
			result.setStatus(1);
			result.setMsg("账号不存在");
			return result;
		}
		//将用户输入的密码加密处理
//		String md5_pwd = NoteUtil.md5(password);
		//将加密后的密码和数据库中加密的结果对比
		if(!user.getPassword().equals(password)){
			result.setStatus(2);
			result.setMsg("密码不正确");
			return result;
		}
		
		result.setStatus(0);
		result.setData(name);
		result.setMsg("登陆成功");
		return result;
	}
	public boolean checkRegister(User user) {
		if(userDao.findById(user.getId())!=null){
			return false;
		}
		return true;
	}


}

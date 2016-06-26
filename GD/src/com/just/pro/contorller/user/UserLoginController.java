package com.just.pro.contorller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.just.pro.bean.NoteResult;
import com.just.pro.service.UserService;

/**
 * 登陆控制
 * @author zhangch
 *
 */
@Controller
public class UserLoginController {
	@Resource
	private UserService userService;
	@RequestMapping("/login.do")
	@ResponseBody//将返回值编程json输出
	public NoteResult execute(String name,String password){
		NoteResult result = userService.checkLogin(name, password);
		return result;
	}
}

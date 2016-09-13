package com.just.pro.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.just.pro.bean.NoteResult;
import com.just.pro.bean.User;
import com.just.pro.dao.UserMapperDao;
import com.just.pro.service.UserService;

@Service("userService")//鎵弿
public class UserServiceImpl implements UserService{
	@Resource//娉ㄥ叆
	private UserMapperDao userDao;
	
	public NoteResult checkLogin(String name, String password) {
		NoteResult result = new NoteResult();
		User user = userDao.findById(name);
		if(user==null){
			result.setStatus(1);
			result.setMsg("璐﹀彿涓嶅瓨鍦�);
			return result;
		}
		//灏嗙敤鎴疯緭鍏ョ殑瀵嗙爜鍔犲瘑澶勭悊
//		String md5_pwd = NoteUtil.md5(password);
		//灏嗗姞瀵嗗悗鐨勫瘑鐮佸拰鏁版嵁搴撲腑鍔犲瘑鐨勭粨鏋滃姣�
		if(!user.getPassword().equals(password)){
			result.setStatus(2);
			result.setMsg("瀵嗙爜涓嶆纭�);
			return result;
		}
		
		result.setStatus(0);
		result.setData(name);
		result.setMsg("鐧婚檰鎴愬姛");
		System.out.println();
		return result;
	}
	public boolean checkRegister(User user) {
		if(userDao.findById(user.getId())!=null){
			return false;
		}
		return true;
	}


}

package com.just.pro.service;

import com.just.pro.bean.NoteResult;

public interface UserService {
	public NoteResult checkLogin(String name,String password);
}

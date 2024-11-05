package com.example.service.impl;

import com.example.entity.Users;
import com.example.form.UsersForm;
import com.example.repository.UsersMapper;
import com.example.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{

	private final UsersMapper usersMapper;
	
	@Override
	public Users userFindById(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return usersMapper.usersSelectById(id);
	}
	
	@Override
	public Users userFindByUsername(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return usersMapper.usersSelectByUsername(username);
	}

	@Override
	public void userInsert(UsersForm usersForm) {
		// TODO 自動生成されたメソッド・スタブ
		usersMapper.usersInsert(usersForm);
	}


}

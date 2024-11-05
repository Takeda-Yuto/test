package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Users;
import com.example.form.UsersForm;

@Service
public interface UsersService {
	
	public Users userFindById(int id);
	
	public Users userFindByUsername(String username);
	
	public void userInsert(UsersForm usersForm);

}

package com.example.repository;

import com.example.entity.Users;
import com.example.form.UsersForm;

public interface UsersMapper {
	
	public Users usersSelectById(int id);
	
	public Users usersSelectByUsername(String username);
	
	public void usersInsert(UsersForm usersForm);

}

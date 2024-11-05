package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.entity.LoginUser;
import com.example.entity.Users;
import com.example.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{

	private final UsersService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		Users user = service.userFindByUsername(username);
		
		if(user != null) {//あればそのデータが持つ情報をログイン処理用インスタンスに格納
			return new LoginUser(user.getUsername(),//ユーザー名
					user.getPassword(),				//パスワード
					getAuthorityList(user.getAuthority()));//認可
		}else {//なければエラーを表示する
			throw new UsernameNotFoundException(username +" => 指定しているユーザー名は存在しません");
		}
	}
	
	List<GrantedAuthority> getAuthorityList(String authority) {
		List<GrantedAuthority> rolelist = new ArrayList<>();
		if(authority.equals("ADMIN")) {//メソッド引数の中身がADMINなら全ての権限を持たせる
			rolelist.add(new SimpleGrantedAuthority("USER"));
			rolelist.add(new SimpleGrantedAuthority("ADMIN"));
		}else {//違うならUSER権限のみ持たせる
			rolelist.add(new SimpleGrantedAuthority("USER"));
		}
		return rolelist;
	}

}

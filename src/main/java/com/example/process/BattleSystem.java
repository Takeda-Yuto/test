package com.example.process;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.entity.Unit;

@Component
public class BattleSystem {
	Random rand = new Random();
	
	
	public void comand(int c,Unit u,Unit enemy) {
		switch(c) {
		case 0 ->{
			attack(u,enemy);
		}
		case 1 ->{
			magic(u,enemy);
		}
		case 2 ->{
			guard(u);
		}
		case 3 ->{
			surrender(u);
		}
		}
	}
	
	public String showMessage(Unit u) {
		return "";
	}
	
	void attack(Unit u,Unit enemy) {
		System.out.println(u.getName() +"が"+ enemy.getName() +"に攻撃！");
		if(rand.nextInt(101) < 80+(u.getSkl()*2+u.getLuck())-(enemy.getAgi()*2+enemy.getLuck())) {
			int d = (u.getStr()-enemy.getDef())+rand.nextInt(3);
			if(d < 0) {
				d = 0;
			}
			if(enemy.isGuard()) {
				d /= 2;
				enemy.setHp(enemy.getHp() -d);
			}else {
				enemy.setHp(enemy.getHp() -d);
			}
			enemy.setDamage(d);
			System.out.println(enemy.getName() +"は"+ d +"のダメージを受けた！");
		}else {
			enemy.setDamage(0);
		}
	}
	
	void magic(Unit u,Unit enemy) {
		System.out.println(u.getName() +"が"+ u.getName() +"に攻撃！");
		if(u.getMp() <= 0) {
			return;
		}
		u.setMp(u.getMp()-2);
		int d = (u.getInte()-enemy.getMdf())+rand.nextInt(1,6);
		if(d < 0) {
			d = 0;
		}
		if(enemy.isGuard()) {
			d /= 2;
			enemy.setHp(enemy.getHp() -d);
		}else {
			enemy.setHp(enemy.getHp() -d);
		}
		enemy.setDamage(d);
		System.out.println(enemy.getName() +"は"+ d +"のダメージを受けた！");
	}

	void guard(Unit u) {
		System.out.println(u.getName() +"は身を守っている");
		u.setGuard(true);
	}
	
	void surrender(Unit u) {
		
	}

}

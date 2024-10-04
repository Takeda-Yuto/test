package com.example.entity;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleData {
	private int turn;
	private int comand;
	private int ecomand;
	private String eimage;
	
	public BattleData(int turn ,int comand,int ecomand) {
		this.turn = turn;
		this.comand = comand;
		this.ecomand = ecomand;
		setEimage();
	}
	
	public void setEcomand() {
		Random rand = new Random();
		this.ecomand = rand.nextInt(3);
	}
	
	public void setEimage() {
		Random rand = new Random();
		switch(rand.nextInt(4)) {
			case 0 ->{
				this.eimage = "images/animal_bear_kowai.png";
			}
			case 1 ->{
				this.eimage = "images/animal_bull_kowai.png";
			}
			case 2 ->{
				this.eimage = "images/character_game_mimic.png";
			}
			case 3 ->{
				this.eimage = "images/fantasy_game_character_slime.png";
			}
		}
	}
	

}

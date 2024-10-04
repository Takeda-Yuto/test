package com.example.entity;


import java.util.Random;

import com.example.form.UnitForm;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unit {
	//キャラid
	private int id;
	//キャラ名
	private String name;
	//最大HP
	private int mhp;
	//現在HP
	private int hp;
	//最大MP
	private int mmp;
	//現在MP
	private int mp;
	//力
	private int str;
	//魔力
	private int inte;
	//技
	private int skl;
	//速さ
	private int agi;
	//守備
	private int def;
	//魔防
	private int mdf;
	//幸運
	private int luck;
	//防御判定
	@Transient
	private boolean guard = false;
	//被ダメージ
	@Transient
	private int damage = 0;
	
	public int getAllStatus() {
		return this.mhp/5 + this.mmp/2 + this.str + this.inte + this.skl + this.agi + this.def + this.mdf + this.luck;
	}
	
	public void setMhp(int mhp) {
		this.mhp = mhp;
		this.hp = mhp;
	}
	
	public void setMmp(int mmp) {
		this.mmp = mmp;
		this.mp = mmp;
	}
	
	public void statusUp(UnitForm uf) {
		setMhp(this.mhp + uf.getMhp());
		setMmp(this.mmp + uf.getMmp());
		setStr(this.str + uf.getStr());
		setInte(this.inte + uf.getInte());
		setSkl(this.skl + uf.getSkl());
		setAgi(this.agi + uf.getAgi());
		setDef(this.def + uf.getDef());
		setMdf(this.mdf + uf.getMdf());
		setLuck(this.luck + uf.getLuck());
	}
	
	public static Unit randomStatus(int id) {
		Random rand = new Random();
		int mhp =5+id;
		int mmp =id;
		int str =id;
		int inte =id;
		int skl =0;
		int agi =0;
		int def =0;
		int mdf =0;
		int luck =0;
		for(int i=0; i<id*3+10; i++) {
			switch(rand.nextInt(9)) {
			case 0 ->{
				mhp++;
			}
			case 1 ->{
				mmp++;
			}
			case 2 ->{
				str++;
			}
			case 3 ->{
				inte++;
			}
			case 4 ->{
				skl++;
			}
			case 5 ->{
				agi++;
			}
			case 6 ->{
				def++;
			}
			case 7 ->{
				mdf++;
			}
			case 8 ->{
				luck++;
			}
			}
		}
		
		return new Unit(id,"enemy",mhp*5,mhp*5,mmp*2,mmp*2,str,inte,skl,agi,def,mdf,luck,false,0);
	}
	
}

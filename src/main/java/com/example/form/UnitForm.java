package com.example.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitForm {
	//キャラid
		private int id;
		//キャラ名
		@NotBlank(message = "名前を入力してください")
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
		
		public int getAllStatus() {
			return this.mhp/5 + this.mmp/2 + this.str + this.inte + this.skl + this.agi + this.def + this.mdf + this.luck;
		}
		
		public void upMhp() {
			this.mhp *= 5;
		}
		
		public void upMmp() {
			this.mmp *= 2;
		}

}

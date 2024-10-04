package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sp {
	private int sp;
	
	public Sp() {
		this.sp = 0;
	}

}

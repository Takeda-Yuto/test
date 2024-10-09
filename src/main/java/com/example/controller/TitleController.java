package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.BattleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TitleController {
	
	private final BattleService battleService;
	
	@GetMapping("/title")
	public String goTitle() {
		battleService.resetUnit();
		return "title";
	}
	


}

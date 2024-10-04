package com.example.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Unit;
import com.example.repository.UnitMapper;
import com.example.service.BattleService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BattleServiceImpl implements BattleService{
	
	//依存関係
	private final UnitMapper unitMapper;

	@Override
	public Unit getMyUnit() {
		// TODO 自動生成されたメソッド・スタブ
		return unitMapper.selectMyUnit();
	}

	@Override
	public Unit getEnemyUnit(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return unitMapper.selectEnemyUnit(id);
	}

	@Override
	public void updateMyUnit(Unit unit) {
		// TODO 自動生成されたメソッド・スタブ
		unitMapper.updateMyUnit(unit);
	}

	@Override
	public void makeEnemyUnit(Unit unit) {
		// TODO 自動生成されたメソッド・スタブ
		unitMapper.createEnemyUnit(unit);
	}
	
	

}

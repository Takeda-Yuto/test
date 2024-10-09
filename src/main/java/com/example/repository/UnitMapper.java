package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.Unit;

@Mapper
public interface UnitMapper {
	//自キャラ取得
	Unit selectMyUnit();
	//敵キャラ取得
	Unit selectEnemyUnit(int id);
	//キャラ登録・更新
	void updateMyUnit(Unit unit);
	//敵情報作成
	void createEnemyUnit(Unit unit);
	//初期化処理１
	void dropUnit();
	//初期化処理２
	void createUnit();
	//初期化処理３
	void resetMyUnit();
}

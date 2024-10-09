package com.example.service;

import com.example.entity.Unit;

public interface BattleService {
	//自キャラ情報の取得
	Unit getMyUnit();
	//敵キャラ情報の取得(idで識別)
	Unit getEnemyUnit(int id);
	//自キャラ情報の登録・更新
	void updateMyUnit(Unit unit);
	//敵キャラの作成
	void makeEnemyUnit(Unit unit);
	//初期化処理
	void resetUnit();
}

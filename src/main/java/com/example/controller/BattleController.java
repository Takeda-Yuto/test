package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.BattleData;
import com.example.entity.Sp;
import com.example.entity.Unit;
import com.example.form.UnitForm;
import com.example.process.BattleSystem;
import com.example.service.BattleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/play")
@RequiredArgsConstructor
public class BattleController {
	
	private final BattleService battleService;
	private final BattleSystem battleSystem;
	
	private Unit player;
	private Unit enemy;
	private Sp sp = new Sp();
	
	//キャラメイク
	@GetMapping("/start")
	public String makeUnit(Model model,UnitForm unitForm) {
		player = battleService.getMyUnit();
		unitForm.setName(player.getName());
		this.sp.setSp(5);
		model.addAttribute("player",player);
		model.addAttribute("sp",this.sp);
		model.addAttribute("unitForm",unitForm);
		model.addAttribute("make","キャラクターメイク");
		return "play/build";
	}
	
	//ステ振り
	@GetMapping("/making")
	public String upStatus(Model model,@ModelAttribute UnitForm unitForm,RedirectAttributes attributes) {
		if(unitForm.getName().isBlank()) {
			model.addAttribute("player",player);
			model.addAttribute("sp",this.sp);
			model.addAttribute("unitForm",unitForm);
			model.addAttribute("make","キャラクターメイク");
			model.addAttribute("noname","名無しは許しません");
			return "play/build";
		}
//		System.out.println(unitForm.getId());
//		System.out.println(unitForm.getName());
//		System.out.println(unitForm.getMhp());
//		System.out.println(unitForm.getHp());
//		System.out.println(unitForm.getMmp());
//		System.out.println(unitForm.getMp());
//		System.out.println(unitForm.getStr());
//		System.out.println(unitForm.getInte());
//		System.out.println(unitForm.getSkl());
//		System.out.println(unitForm.getAgi());
//		System.out.println(unitForm.getDef());
//		System.out.println(unitForm.getMdf());
//		System.out.println(unitForm.getLuck());
//		System.out.println(this.sp.getSp());
		unitForm.upMhp();
		unitForm.upMmp();
		//spを超える数値を設定したら
		if(unitForm.getAllStatus() > sp.getSp()) {
			model.addAttribute("message","ポイントを超えないように振り分けてください");
			model.addAttribute("player",player);
			model.addAttribute("sp",this.sp);
			model.addAttribute("unitForm",unitForm);
			model.addAttribute("make","キャラクターメイク");
			return "play/build";
		}
		sp.setSp(sp.getSp() - unitForm.getAllStatus());
		player.statusUp(unitForm);
		if(unitForm.getName() != null) {
			player.setName(unitForm.getName());
			model.addAttribute("name",unitForm.getName());
		}
		battleService.updateMyUnit(player);
		model.addAttribute("player",player);
		model.addAttribute("unitForm",unitForm);
		return "play/result";
	}
	
	//戦闘画面
	@GetMapping("/battle")
	public String showBattle(Model model) {
		//ステータスから戦う敵の強さを選定
		int id = player.getAllStatus()/5-5;
		enemy = battleService.getEnemyUnit(id);
		//データに存在しないidを参照した場合はそのidで敵作成
		if(enemy == null) {
			battleService.makeEnemyUnit(Unit.randomStatus(id));
			enemy = battleService.getEnemyUnit(id);
		}
		model.addAttribute("player",player);
		model.addAttribute("enemy",enemy);
		model.addAttribute("battleForm",new BattleData(1,0,0));
		model.addAttribute("systemlog",enemy.getName() +"が現れた");
		return "play/battle";
	}
	//攻撃コマンド
	@GetMapping("/battle/attack")
	public String comandAttack(Model model,@ModelAttribute BattleData battleData) {
		//ターン経過とコマンド処理
		battleData.setTurn(battleData.getTurn()+1);
		battleData.setComand(0);
		battleData.setEcomand();
		player.setGuard(false);
		battleSystem.comand(battleData.getComand(), player, enemy);
		enemy.setGuard(false);
		battleSystem.comand(battleData.getEcomand(), enemy, player);
		//コマンド実行後の情報を格納
		model.addAttribute("player",player);
		model.addAttribute("enemy",enemy);
		model.addAttribute("battleForm",battleData);
		//攻撃
		model.addAttribute("comandlog",player.getName() +"の攻撃！");
		if(enemy.getDamage() == 0) {
			model.addAttribute("playerlog","ミス　ダメージを与えられなかった");
		}else {
			model.addAttribute("playerlog",enemy.getName() +"は"+ enemy.getDamage() +"ポイントのダメージを与えた");
		}
		//敵のHPが尽きたとき
		if(enemy.getHp() <= 0) {
			model.addAttribute("deathlog",enemy.getName() +"は力尽きた");
			return "play/battle";
		}
		//敵の各種行動
		//攻撃
		if(battleData.getEcomand() == 0) {
			model.addAttribute("ecomandlog",enemy.getName() +"の攻撃！");
			if(player.getDamage() == 0) {
				model.addAttribute("enemylog",player.getName() +"はダメージを受けなかった");
			}else {
				model.addAttribute("enemylog",player.getName() +"は"+ player.getDamage() +"ポイントのダメージを受けた");
			}
		}
		//魔法
		if(enemy.getMp() <= 0 && battleData.getEcomand() == 1) {
			model.addAttribute("ecomandlog",enemy.getName() +"の魔法！　しかし　MPが足りない！");
		}else if(battleData.getEcomand() == 1){
			model.addAttribute("ecomandlog",enemy.getName() +"の魔法！");
			if(player.getDamage() == 0) {
				model.addAttribute("enemylog",player.getName() +"はダメージを受けなかった");
			}else {
				model.addAttribute("enemylog",player.getName() +"は"+ player.getDamage() +"ポイントのダメージを受けた");
			}
		}
		//防御
		if(battleData.getEcomand() == 2){
			model.addAttribute("ecomandlog",enemy.getName() +"は防御している");
		}
		//プレイヤーのHPが尽きたとき
		if(player.getHp() <= 0) {
			model.addAttribute("deathlog",player.getName() +"は力尽きた");
			return "play/battle";
		}
		System.out.println(battleData.getTurn());
		System.out.println(battleData.getComand());
		return "play/battle";
	}
	//魔法コマンド
	@GetMapping("/battle/magic")
	public String comandMagic(Model model,@ModelAttribute BattleData battleData) {
		//ターン経過とコマンド処理
		battleData.setTurn(battleData.getTurn()+1);
		battleData.setComand(1);
		battleData.setEcomand();
		player.setGuard(false);
		battleSystem.comand(battleData.getComand(), player, enemy);
		enemy.setGuard(false);
		battleSystem.comand(battleData.getEcomand(), enemy, player);
		//コマンド実行後の情報を格納
		model.addAttribute("player",player);
		model.addAttribute("enemy",enemy);
		model.addAttribute("battleForm",battleData);
		//魔法
		if(player.getMp() < 0) {
			model.addAttribute("comandlog",player.getName() +"の魔法！　しかし　MPが足りない！");
		}else {
			model.addAttribute("comandlog",player.getName() +"の魔法！");
			if(enemy.getDamage() == 0) {
				model.addAttribute("playerlog","ミス　ダメージを与えられなかった");
			}else {
				model.addAttribute("playerlog",enemy.getDamage() +"ポイントのダメージを与えた");
			}
		}
		//敵のHPが尽きたとき
		if(enemy.getHp() <= 0) {
			model.addAttribute("deathlog",enemy.getName() +"は力尽きた");
			return "play/battle";
		}
		//敵の各種行動
		//攻撃
		if(battleData.getEcomand() == 0) {
			model.addAttribute("ecomandlog",enemy.getName() +"の攻撃！");
			if(player.getDamage() == 0) {
			model.addAttribute("enemylog",player.getName() +"はダメージを受けなかった");
			}else {
			model.addAttribute("enemylog",player.getName() +"は"+ player.getDamage() +"ポイントのダメージを受けた");
			}
		}
		//魔法
		if(enemy.getMp() <= 0 && battleData.getEcomand() == 1) {
			model.addAttribute("ecomandlog",enemy.getName() +"の魔法！　しかし　MPが足りない！");
		}else if(battleData.getEcomand() == 1){
			model.addAttribute("ecomandlog",enemy.getName() +"の魔法！");
			if(player.getDamage() == 0) {
				model.addAttribute("enemylog",player.getName() +"はダメージを受けなかった");
			}else {
				model.addAttribute("enemylog",player.getName() +"は"+ player.getDamage() +"ポイントのダメージを受けた");
			}
		}
		//防御
		if(battleData.getEcomand() == 2){
			model.addAttribute("ecomandlog",enemy.getName() +"は防御している");
		}
		//プレイヤーのHPが尽きたとき
		if(player.getHp() <= 0) {
			model.addAttribute("deathlog",player.getName() +"は力尽きた");
			return "play/battle";
		}
		
		return "play/battle";
	}
	//防御コマンド
	@GetMapping("/battle/guard")
	public String comandGuard(Model model,@ModelAttribute BattleData battleData) {
		//ターン経過とコマンド処理
		battleData.setTurn(battleData.getTurn()+1);
		battleData.setComand(2);
		battleData.setEcomand();
		player.setGuard(false);
		battleSystem.comand(battleData.getComand(), player, enemy);
		enemy.setGuard(false);
		battleSystem.comand(battleData.getEcomand(), enemy, player);
		//コマンド実行後の情報を格納
		model.addAttribute("player",player);
		model.addAttribute("enemy",enemy);
		model.addAttribute("battleForm",battleData);
		//防御
		model.addAttribute("playerlog",player.getName() +"は防御している");
		//敵の各種行動
		//攻撃
		if(battleData.getEcomand() == 0) {
			model.addAttribute("ecomandlog",enemy.getName() +"の攻撃！");
			if(player.getDamage() == 0) {
				model.addAttribute("enemylog",player.getName() +"はダメージを受けなかった");
			}else {
				model.addAttribute("enemylog",player.getName() +"は"+ player.getDamage() +"ポイントのダメージを受けた");
			}
		}
		//魔法
		if(enemy.getMp() <= 0 && battleData.getEcomand() == 1) {
			model.addAttribute("ecomandlog",enemy.getName() +"の魔法！　しかし　MPが足りない！");
		}else if(battleData.getEcomand() == 1){
			model.addAttribute("ecomandlog",enemy.getName() +"の魔法！");
			if(player.getDamage() == 0) {
				model.addAttribute("enemylog",player.getName() +"はダメージを受けなかった");
			}else {
				model.addAttribute("enemylog",player.getName() +"は"+ player.getDamage() +"ポイントのダメージを受けた");
			}
		}
		//防御
		if(battleData.getEcomand() == 2){
			model.addAttribute("ecomandlog",enemy.getName() +"は防御している");
		}
		//プレイヤーのHPが尽きたとき
		if(player.getHp() <= 0) {
			model.addAttribute("deathlog",player.getName() +"は力尽きた");
			return "play/battle";
		}
		
		return "play/battle";
	}
	//降参コマンド
	@GetMapping("/battle/surrender")
	public String comandSurrender(Model model,@ModelAttribute BattleData battleData) {
		battleData.setTurn(battleData.getTurn()+1);
		battleData.setComand(3);
		player.setGuard(false);
		enemy.setGuard(false);
		battleSystem.comand(battleData.getComand(), player, enemy);
		model.addAttribute("player",player);
		model.addAttribute("enemy",enemy);
		model.addAttribute("battleForm",battleData);
		model.addAttribute("systemlog",player.getName() +"は降参した");
		return "play/battle";
	}
	
	//リザルト画面
	@GetMapping("/result")
	public String result(Model model,@ModelAttribute Unit unit,@ModelAttribute UnitForm unitForm) {
		sp.setSp(sp.getSp() + 5);
		unitForm.setName(player.getName());
		model.addAttribute("player",player);
		model.addAttribute("sp",sp);
		model.addAttribute("unitForm",unitForm);
		model.addAttribute("win","勝利");
		return "play/build";
	}

}

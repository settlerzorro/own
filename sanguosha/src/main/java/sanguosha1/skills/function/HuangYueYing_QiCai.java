package sanguosha1.skills.function;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Function;
import sanguosha1.skills.LockingSkillIF;

/**
 * 黄月英【奇才】
 * @author user
 *
 */
public class HuangYueYing_QiCai extends P_Function implements LockingSkillIF{
	AbstractPlayer player;
	public HuangYueYing_QiCai(AbstractPlayer player) {
		super(player);
	}
	
	/**
	 * 重写锦囊使用范围：无限制
	 */
	@Override
	public int getKitUseDistance() {
		return 999;
	}
	
	@Override
	public String getName() {
		return "奇才";
	}
}

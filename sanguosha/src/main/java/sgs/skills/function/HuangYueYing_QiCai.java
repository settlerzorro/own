package sgs.skills.function;

import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Function;
import sgs.skills.LockingSkillIF;

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

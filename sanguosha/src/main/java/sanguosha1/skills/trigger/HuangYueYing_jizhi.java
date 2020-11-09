package sanguosha1.skills.trigger;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Trigger;
import sanguosha1.skills.LockingSkillIF;

/**
 * 黄月英【集智】
 * @author user
 *
 */
public class HuangYueYing_jizhi extends P_Trigger implements LockingSkillIF{
	public HuangYueYing_jizhi(AbstractPlayer p){
		this.player = p;
	}
	/**
	 * 【黄月英】
	 * 重写使用锦囊触发
	 */
	@Override
	public void afterMagic() {
		player.getAction().addOneCardFromList();
		player.refreshView();
	}

	@Override
	public String getName() {
		return "集智";
	}

}

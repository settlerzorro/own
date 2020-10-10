package sgs.skills.process;

import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Process;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;

/**
 * 貂蝉【闭月】
 * 回合结束得1张手牌
 * @author user
 *
 */
public class DiaoChan_biyue extends P_Process implements LockingSkillIF{
	private static final int NUMBER = 1;
	public DiaoChan_biyue(AbstractPlayer p) {
		super(p);
	}
	
	/**
	 * 重写回合结束
	 */
	@Override
	public void stage_end() {
		for (int i = 0; i < NUMBER; i++) {			
			player.getAction().addOneCardFromList();
		}
		//player.refreshView();
		super.stage_end();
		ViewManagement.getInstance().printBattleMsg(player.getInfo().getName()+getName()+"触发");
	}

	@Override
	public String getName() {
		return "闭月";
	}

}

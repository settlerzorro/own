package sgs.skills.trigger;

import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Trigger;
import sgs.skills.LockingSkillIF;

/**
 * 张飞【咆哮】
 * @author user
 *
 */
public class ZhangFei_paoxiao extends P_Trigger implements LockingSkillIF{
	
	public ZhangFei_paoxiao(AbstractPlayer p){
		this.player = p;
	}
	
	/**
	 * 重写杀后触发
	 * 取消开关
	 */
	@Override
	public void afterSha() {
		super.afterSha();
		player.getState().setUsedSha(false);
	}

	@Override
	public String getName() {
		return "咆哮";
	}

}

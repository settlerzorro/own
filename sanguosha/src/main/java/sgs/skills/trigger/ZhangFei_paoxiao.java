package sgs.skills.trigger;

import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Trigger;
import sgs.skills.LockingSkillIF;

/**
 * �ŷɡ�������
 * @author user
 *
 */
public class ZhangFei_paoxiao extends P_Trigger implements LockingSkillIF{
	
	public ZhangFei_paoxiao(AbstractPlayer p){
		this.player = p;
	}
	
	/**
	 * ��дɱ�󴥷�
	 * ȡ������
	 */
	@Override
	public void afterSha() {
		super.afterSha();
		player.getState().setUsedSha(false);
	}

	@Override
	public String getName() {
		return "����";
	}

}

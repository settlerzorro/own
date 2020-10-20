package sanguosha1.skills.trigger;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Trigger;
import sanguosha1.skills.LockingSkillIF;

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

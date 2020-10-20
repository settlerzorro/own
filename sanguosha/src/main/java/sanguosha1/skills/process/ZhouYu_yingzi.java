package sanguosha1.skills.process;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Process;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

/**
 * ��褼��ܡ�Ӣ�ˡ�
 * ���ƽ׶ο�����3����
 * @author user
 *
 */
public class ZhouYu_yingzi extends P_Process implements LockingSkillIF{

	public ZhouYu_yingzi(AbstractPlayer p) {
		super(p);
	}

	/**
	 * ��д���ƽ׶�
	 */
	@Override
	public void stage_addCards() {
		super.stage_addCards();
		ViewManagement.getInstance().printBattleMsg("��褡�Ӣ�ˡ�����");
		player.getAction().addOneCardFromList();
	}

	@Override
	public String getName() {
		return "Ӣ��";
	}
	
}

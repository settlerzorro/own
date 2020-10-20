package sanguosha1.skills.process;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Process;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

/**
 * ���������¡�
 * �غϽ�����1������
 * @author user
 *
 */
public class DiaoChan_biyue extends P_Process implements LockingSkillIF{
	private static final int NUMBER = 1;
	public DiaoChan_biyue(AbstractPlayer p) {
		super(p);
	}
	
	/**
	 * ��д�غϽ���
	 */
	@Override
	public void stage_end() {
		for (int i = 0; i < NUMBER; i++) {			
			player.getAction().addOneCardFromList();
		}
		//player.refreshView();
		super.stage_end();
		ViewManagement.getInstance().printBattleMsg(player.getInfo().getName()+getName()+"����");
	}

	@Override
	public String getName() {
		return "����";
	}

}

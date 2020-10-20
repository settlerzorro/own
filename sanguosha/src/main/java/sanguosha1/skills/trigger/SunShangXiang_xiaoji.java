package sanguosha1.skills.trigger;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Trigger;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

/**
 * ������ġ��ɼ���
 * ��ʧװ���󣬻��2������
 * @author user
 *
 */
public class SunShangXiang_xiaoji extends P_Trigger implements LockingSkillIF{
	private static final int NUMBER = 2;
	public SunShangXiang_xiaoji(AbstractPlayer p) {
		this.player = p;
	}
	
	/**
	 * ��дж��װ������
	 */
	@Override
	public void afterUnloadEquipmentCard() {
		ViewManagement.getInstance().printBattleMsg("���ɼ������ܴ���");
		for (int i = 0; i < NUMBER; i++) {
			player.getAction().addOneCardFromList();			
		}
	}

	@Override
	public String getName() {
		return "�ɼ�";
	}
	
}

package sanguosha1.skills.function;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Function;
import sanguosha1.skills.LockingSkillIF;

/**
 * ����Ӣ����š�
 * @author user
 *
 */
public class HuangYueYing_QiCai extends P_Function implements LockingSkillIF{
	AbstractPlayer player;
	public HuangYueYing_QiCai(AbstractPlayer player) {
		super(player);
	}
	
	/**
	 * ��д����ʹ�÷�Χ��������
	 */
	@Override
	public int getKitUseDistance() {
		return 999;
	}
	
	@Override
	public String getName() {
		return "���";
	}
}

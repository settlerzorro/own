package sgs.skills.function;

import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Function;
import sgs.skills.LockingSkillIF;

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

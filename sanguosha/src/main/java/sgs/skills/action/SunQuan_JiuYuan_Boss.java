package sgs.skills.action;

import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Action;
import sgs.skills.LockingSkillIF;

/**
 * ��Ȩ����Ԯ��
 * @author user
 *
 */
public class SunQuan_JiuYuan_Boss extends P_Action implements LockingSkillIF{
	
	public SunQuan_JiuYuan_Boss(AbstractPlayer p) {
		super(p);
	}

	/**
	 * ��д����������
	 */
	@Override
	public void taoSave(AbstractPlayer p) {
		super.taoSave(p);
		if(p!=player && p.getInfo().getCountry()==player.getInfo().getCountry()){
			player.getAction().addHP(1);
		}
	}

	@Override
	public String getName() {
		return "��Ԯ";
	}
}

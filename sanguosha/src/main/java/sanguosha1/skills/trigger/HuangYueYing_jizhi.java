package sanguosha1.skills.trigger;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Trigger;
import sanguosha1.skills.LockingSkillIF;

/**
 * ����Ӣ�����ǡ�
 * @author user
 *
 */
public class HuangYueYing_jizhi extends P_Trigger implements LockingSkillIF{
	public HuangYueYing_jizhi(AbstractPlayer p){
		this.player = p;
	}
	/**
	 * ������Ӣ��
	 * ��дʹ�ý��Ҵ���
	 */
	@Override
	public void afterMagic() {
		player.getAction().addOneCardFromList();
		player.refreshView();
	}

	@Override
	public String getName() {
		return "����";
	}

}

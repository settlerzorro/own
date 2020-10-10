package sgs.skills.trigger;

import sgs.card.AbstractCard;
import sgs.data.enums.Colors;
import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Trigger;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;

/**
 * �ĺ���ܡ����ҡ�
 * ���˺��˺���Դ�ж�������Ϊ�����1��Ѫ�����߶���2������
 * @author user
 *
 */
public class XiaHouDun_ganglie extends P_Trigger implements LockingSkillIF{

	public XiaHouDun_ganglie(AbstractPlayer p) {
		this.player = p;
	}
	/**
	 * ��д���˴���
	 */
	@Override
	public void afterLoseHP(AbstractPlayer murderer) {
		AbstractCard cc = ModuleManagement.getInstance().showOneCheckCard();
		if(player.getFunction().checkRollCard(cc, Colors.HEITAO,Colors.FANGKUAI,Colors.MEIHUA)){
			murderer.getAction().loseHP(1, null);
			ViewManagement.getInstance().printChatMsg("["+player.getInfo().getName()+"]"+"�󱲣���������");
		}
	}
	@Override
	public String getName() {
		return "����";
	}
	
	
}

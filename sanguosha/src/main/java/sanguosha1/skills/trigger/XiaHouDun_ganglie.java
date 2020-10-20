package sanguosha1.skills.trigger;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Trigger;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

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

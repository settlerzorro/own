package sanguosha1.skills.trigger;

import sanguosha1.card.AbstractCard;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Trigger;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

/**
 * �ܲټ��ܡ����ۡ�
 * ���˺�����˺�������������
 * ��Ҵ�����
 * --��д���˴����¼��� ��Ѫ������˺���������������
 * @author user
 *
 */
public class CaoCao_jianxiong extends P_Trigger implements LockingSkillIF{
	public CaoCao_jianxiong(){}
	public CaoCao_jianxiong(AbstractPlayer p) {
		this.player = p;
	}
	/**
	 * ��д��Ѫ����
	 */
	@Override
	public void afterLoseHP(AbstractPlayer murderer) {
		if(murderer==null || murderer.getState().getUsedCard().isEmpty())return;
		//��ӡ��Ϣ
		ViewManagement.getInstance().printBattleMsg(player.getInfo().getName()+"�������ܡ�"+getName()+"��");
		//�����ֳ���������
		for (AbstractCard c  : murderer.getState().getUsedCard()) {
			player.getAction().addCardToHandCard(c);
			ModuleManagement.getInstance().getGcList().remove(c);
		}	
	}
	
	@Override
	public String getName() {
		return "����";
	}
}
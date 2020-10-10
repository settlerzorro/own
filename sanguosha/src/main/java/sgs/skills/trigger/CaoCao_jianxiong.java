package sgs.skills.trigger;

import sgs.card.AbstractCard;
import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Trigger;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;

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
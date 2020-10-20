package sanguosha1.skills.action;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Action;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

/**
 * �����ܡ����
 * 
 * @author user
 *
 */
public class MaChao_tieji extends P_Action implements LockingSkillIF{

	public MaChao_tieji(AbstractPlayer p) {
		super(p);
	}

	/**
	 * ��дɱ
	 * �ж������Ϊ���ң�ֱ�ӿ�Ѫ
	 */
	@Override
	public boolean sha(AbstractPlayer p) {
		AbstractCard cc = ModuleManagement.getInstance().showOneCheckCard();
		//����ж�Ϊ����
		if(player.getFunction().checkRollCard(cc, Colors.HONGXIN)){
			ViewManagement.getInstance().printBattleMsg(player.getInfo().getName()+getName()+"��Ч");
			p.getAction().loseHP(1+player.getState().getExtDamage(), player);
			//����
			player.getState().setUsedSha(true);
			//���ô����¼�
			player.getTrigger().afterSha();
			return true;
		}else{
			return super.sha(p);
		}
	}

	@Override
	public String getName() {
		return "����";
	}
}

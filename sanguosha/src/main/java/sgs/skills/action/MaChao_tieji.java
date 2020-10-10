package sgs.skills.action;

import sgs.card.AbstractCard;
import sgs.data.enums.Colors;
import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Action;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;

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

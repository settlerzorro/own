package sgs.skills.action;

import sgs.card.AbstractCard;
import sgs.data.enums.Colors;
import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Action;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;

/**
 * 马超技能【铁骑】
 * 
 * @author user
 *
 */
public class MaChao_tieji extends P_Action implements LockingSkillIF{

	public MaChao_tieji(AbstractPlayer p) {
		super(p);
	}

	/**
	 * 重写杀
	 * 判定，如果为红桃，直接扣血
	 */
	@Override
	public boolean sha(AbstractPlayer p) {
		AbstractCard cc = ModuleManagement.getInstance().showOneCheckCard();
		//如果判定为红桃
		if(player.getFunction().checkRollCard(cc, Colors.HONGXIN)){
			ViewManagement.getInstance().printBattleMsg(player.getInfo().getName()+getName()+"生效");
			p.getAction().loseHP(1+player.getState().getExtDamage(), player);
			//开关
			player.getState().setUsedSha(true);
			//调用触发事件
			player.getTrigger().afterSha();
			return true;
		}else{
			return super.sha(p);
		}
	}

	@Override
	public String getName() {
		return "铁骑";
	}
}

package sanguosha1.skills.action;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Action;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;
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

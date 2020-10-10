package sgs.skills.trigger;

import sgs.card.AbstractCard;
import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Trigger;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;

import java.util.ArrayList;
import java.util.List;

/**
 * 司马懿技能【反馈】
 * 受伤后从伤害来源处收一张牌
 * @author user
 *
 */
public class Simayi_fankui extends P_Trigger implements LockingSkillIF{
	//AbstractPlayer p;
	public Simayi_fankui(){}
	public Simayi_fankui(AbstractPlayer p){
		this.player = p;
	}
	/**
	 * 重写受伤触发
	 */
	@Override
	public void afterLoseHP(AbstractPlayer murderer) {
		if(murderer==null)return;
		List<AbstractCard> list = new ArrayList<AbstractCard>();
		List<AbstractCard> listHand = murderer.getState().getCardList();
		//EquipmentCardIF[] listEq = murderer.getState().getEquipmentList();
		if(listHand.size()>0)list.addAll(listHand);
		//选择一张
		if(list.size()==0){
			return;
		}else{
			ViewManagement.getInstance().printBattleMsg(player.getInfo().getName()+"发动技能【"+getName()+"】");
			player.getAction().addCardToHandCard(list.get(0));
			murderer.getAction().removeCard(list.get(0));
		}
	}
	@Override
	public String getName() {
		return "反馈";
	}
	
	
}

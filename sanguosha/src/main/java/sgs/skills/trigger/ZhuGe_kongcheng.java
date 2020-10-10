package sgs.skills.trigger;

import sgs.data.constant.Const_Game;
import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Trigger;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;

import java.util.Arrays;

/**
 * 诸葛【空城】
 * @author user
 *
 */
public class ZhuGe_kongcheng extends P_Trigger implements LockingSkillIF{
	final Integer[] cards = {Const_Game.SHA,Const_Game.JUEDOU};
	public ZhuGe_kongcheng(AbstractPlayer p){
		super(p);
	}
	
	@Override
	public void afterLoseHandCard() {
		super.afterLoseHandCard();
		if(player.getState().getCardList().isEmpty()){
			ViewManagement.getInstance().printBattleMsg("【空城】触发");
			player.getState().getImmuneCards().addAll(Arrays.asList(cards));
		}
	}
	
	@Override
	public void afterGetHandCard() {
		super.afterGetHandCard();
		if(!player.getState().getCardList().isEmpty()){
			player.getState().getImmuneCards().clear();
		}
	}

	@Override
	public String getName() {
		return "空城";
	}

}

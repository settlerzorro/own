package sanguosha1.skills.trigger;

import java.util.Arrays;

import sanguosha1.data.constant.Const_Game;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Trigger;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

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

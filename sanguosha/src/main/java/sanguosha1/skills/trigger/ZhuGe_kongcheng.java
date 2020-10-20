package sanguosha1.skills.trigger;

import sanguosha1.data.constant.Const_Game;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Trigger;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

import java.util.Arrays;

/**
 * ��𡾿ճǡ�
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
			ViewManagement.getInstance().printBattleMsg("���ճǡ�����");
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
		return "�ճ�";
	}

}

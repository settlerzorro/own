package sanguosha1.skills.trigger;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Trigger;
import sanguosha1.skills.LockingSkillIF;

/**
 * 陆逊【连营】
 * @author user
 *
 */
public class LuXun_lianying extends P_Trigger implements LockingSkillIF{
	public LuXun_lianying(AbstractPlayer p){
		this.player = p;
	}
	
	/**
	 * 重写丢失手牌触发
	 */
	@Override
	public void afterLoseHandCard() {
		//如果无牌则摸一张
		if(player.getState().getCardList().isEmpty()){
			player.getAction().addOneCardFromList();
			System.out.println("牌不是万能的，没牌是万万不能的！");
		}
	}
	
	@Override
	public String getName() {
		return "连营";
	}

}

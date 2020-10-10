package sgs.skills.active;

import sgs.card.AbstractCard;
import sgs.card.changed.Virtual_LeBuSiShu;
import sgs.data.constant.Const_Game;
import sgs.data.enums.Colors;
import sgs.gui.main.Panel_Control;
import sgs.gui.main.Panel_Player;
import sgs.player.AbstractPlayer;
import sgs.player.PlayerIF;
import sgs.skills.SkillIF;

import javax.swing.*;
import java.util.List;

/**
 * 大乔【国色】
 * @author user
 *
 */
public class DaQiao_GuoSe implements Runnable,SkillIF{
	AbstractPlayer player;
	public DaQiao_GuoSe(AbstractPlayer p){
		this.player = p;
	}
	
	@Override
	public void run() {
		final Panel_Control pc = (Panel_Control) player.getPanel();
		//显示所有方块牌
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pc.getHand().unableToUseCard();
				pc.getHand().disableClick();
				pc.getHand().enableOKAndCancel();
				pc.getHand().setTargetCheck(false);
				pc.getHand().remindToUse(Colors.FANGKUAI);
				
				List<Panel_Player> list = pc.getMain().getPlayers();
				for (Panel_Player pp : list) {
					if (!pp.getPlayer().getState().isDead()) {
						pp.enableToUse();
					}
				}
				//pc.getHand().setSelectLimit(1);
			}
		});
		//等待选择
		while(true){
			if(player.getState().getRes()==Const_Game.OK){
				AbstractCard c = pc.getHand().getSelectedList().get(0).getCard();
				//原有的牌丢弃
				c.throwIt(player);
				//新出一张虚拟乐不思蜀
				//new Virtual_GuoHeChaiQiao(c).use(player, pc.getHand().getTarget().getList().get(0));
				new Virtual_LeBuSiShu(c).use(player, pc.getHand().getTarget().getList().get(0));
				break;
			}
			if(player.getState().getRes()==Const_Game.CANCEL){
				player.refreshView();
				break;
			}
		}
		//如果在回合中，就解回合锁
		if(player.getStageNum()==PlayerIF.STAGE_USECARDS){
			synchronized (player.getProcess()) {
				player.getState().setRes(0);
				player.getProcess().notify();
			}
		}		
	}

	@Override
	public String getName() {
		return "国色";
	}

	@Override
	public boolean isEnableUse() {
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}



}

package sanguosha1.skills.active;

import java.util.List;

import javax.swing.SwingUtilities;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.changed.Virtual_LeBuSiShu;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_Player;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.PlayerIF;
import sanguosha1.skills.SkillIF;

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

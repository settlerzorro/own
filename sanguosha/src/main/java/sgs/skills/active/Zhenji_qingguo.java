package sgs.skills.active;

import sgs.data.constant.Const_Game;
import sgs.data.enums.Colors;
import sgs.data.enums.ErrorMessageType;
import sgs.gui.main.Panel_Control;
import sgs.player.AbstractPlayer;
import sgs.service.MessageManagement;
import sgs.skills.ChangeCardIF;
import sgs.skills.SkillIF;

import javax.swing.*;

/**
 * 甄姬技能【倾国】
 * 黑色牌可以当做闪
 * @author user
 *
 */
public class Zhenji_qingguo implements Runnable ,SkillIF,ChangeCardIF{
	AbstractPlayer player;
	public Zhenji_qingguo(AbstractPlayer p){
		this.player = p;
	}
	
	@Override
	public void run() {
		if(!player.getState().isRequest()){
			MessageManagement.printErroMsg(ErrorMessageType.cannotUseNow);
			synchronized (player.getProcess()) {
				player.getState().setRes(0);
				player.getProcess().notify();
			}
			return;
		}
		final Panel_Control pc = (Panel_Control) player.getPanel();
		//显示所有黑色牌
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pc.getHand().unableToUseCard();
				pc.getHand().remindToUse(Colors.HEITAO,Colors.MEIHUA);
				pc.getHand().setSelectLimit(1);
				pc.getHand().enableToClick();
				pc.repaint();
			}
		});
		//等待选择
		while(true){
			if(player.getState().getRes()==Const_Game.OK){
				//AbstractCard c = pc.getHand().getSelectedList().get(0).getCard();
				// 更新手牌
				pc.getHand().updateCards();
				player.getState().setRes(getResultType());
				break;
			}
			if(player.getState().getRes()==Const_Game.CANCEL){
				player.refreshView();
				break;
			}
		}
		
	}

	@Override
	public String getName() {
		return "倾国";
	}

	@Override
	public boolean isEnableUse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getResultType() {
		return Const_Game.SHAN;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}

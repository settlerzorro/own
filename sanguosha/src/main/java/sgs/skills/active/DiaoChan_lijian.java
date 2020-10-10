package sgs.skills.active;

import sgs.data.constant.Const_Game;
import sgs.data.enums.ErrorMessageType;
import sgs.gui.main.Panel_Control;
import sgs.gui.main.Panel_HandCards;
import sgs.gui.main.Panel_Player;
import sgs.player.AbstractPlayer;
import sgs.service.MessageManagement;
import sgs.service.ViewManagement;
import sgs.skills.SkillIF;

import javax.swing.*;
import java.util.List;

/**
 * ?????????
 * 
 * @author user
 * 
 */
public class DiaoChan_lijian implements Runnable, SkillIF {
	AbstractPlayer player;
	boolean	isUsed;
	Panel_Control pc;
	Panel_HandCards ph;

	public DiaoChan_lijian(AbstractPlayer p) {
		this.player = p;
	}

	/**
	 * ?????????? ; ??????????2???????? ; ???OK
	 */
	@Override
	public void run() {
		if(isUsed){
			MessageManagement.printErroMsg(ErrorMessageType.hasUsed);
			//????
			synchronized (player.getProcess()) {
				player.getState().setRes(0);
				player.getProcess().notify();
			}
			return;
		}
		 pc = (Panel_Control) player.getPanel();
		 ph = pc.getHand();
		SwingUtilities.invokeLater(run);
		//??????2???????????????
		while(true){
			if(ph.getTarget().getList().size()==2 && !ph.getSelectedList().isEmpty()){
				ph.enableOKAndCancel();
				break;
			}
			if(player.getState().getRes() == Const_Game.CANCEL){
				break;
			}
		}
		//??????
		while(true){
			if(player.getState().getRes() == Const_Game.OK){
				AbstractPlayer p1 = ph.getTarget().getList().get(0);
				AbstractPlayer p2 = ph.getTarget().getList().get(1);
				ViewManagement.getInstance().printBattleMsg(p1.getInfo().getName()+"????"+p2.getInfo().getName());
				
				ph.getSelectedList().get(0).getCard().throwIt(player);
				player.refreshView();
				while(p1.getAction().jueDou(p2)){
					AbstractPlayer tmp = p1;
					p1=p2;
					p2 = tmp;
				}
				player.getState().setRes(0);
				isUsed = true;
				break;
			}
			if(player.getState().getRes() == Const_Game.CANCEL){
				//player.refreshView();
				ViewManagement.getInstance().refreshAll();
				break;
			}
		}
		//????
		synchronized (player.getProcess()) {
			player.getState().setRes(0);
			player.getProcess().notify();
		}
	}

	@Override
	public String getName() {
		return "???";
	}

	@Override
	public void init() {
		isUsed = false;
	}

	@Override
	public boolean isEnableUse() {
		// TODO Auto-generated method stub
		return false;
	}

	Runnable run = new Runnable() {

		@Override
		public void run() {

			ph.getTarget().setLimit(2);
			//ph.getTarget().getList().clear();
			ph.disableClick();
			ph.enableCancel();
			ph.remindToUseALL();
			ph.setTargetCheck(false);
			List<Panel_Player> list = ph.getMain().getPlayers();
			for (Panel_Player pp : list) {
				if (!pp.getPlayer().getState().isDead()) {
					if(pp.getPlayer().getInfo().isSex()==player.getInfo().isSex()){
						pp.disableToUse();
						continue;
					}
				}
				pp.enableToUse();
			}
		}
	};
}

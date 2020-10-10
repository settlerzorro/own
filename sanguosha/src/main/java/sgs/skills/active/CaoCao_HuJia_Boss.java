package sgs.skills.active;

import sgs.card.AbstractCard;
import sgs.data.constant.Const_Game;
import sgs.gui.main.Panel_Control;
import sgs.gui.main.Panel_HandCards;
import sgs.player.AbstractPlayer;
import sgs.skills.SkillIF;

import javax.swing.*;

/**
 * �ܲ١����ݡ�
 * @author user
 *
 */
public class CaoCao_HuJia_Boss implements Runnable,SkillIF{
	AbstractPlayer player;
	Panel_Control pc;
	
	public CaoCao_HuJia_Boss(AbstractPlayer player){
		this.player = player;
	}
	@Override
	public void run() {
		pc = (Panel_Control) player.getPanel();
		if(!player.getState().isRequest()){
			synchronized (player.getProcess()) {
				player.getState().setRes(0);
				player.getProcess().notify();
			}
			return;
		}
		SwingUtilities.invokeLater(ask);
		while(true){

			if (player.getState().getRes() == Const_Game.OK) {
				// �����ͬ����
				for (AbstractPlayer p : player.getSameCountryPlayers()) {
					if (p.getRequest().requestShan()) {
						AbstractCard c = p.getState().getUsedCard().get(0);
						player.getState().getUsedCard().add(0, c);
						
							player.getState().setRes(Const_Game.SHAN);
							try {
								//��ͣ1��ȷ���źű����ܵ�
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						
						break;
					}
				}
				//���û����
				player.getState().setRes(Const_Game.REDO);
				return;
			}
			if (player.getState().getRes() == Const_Game.CANCEL) {
				break;
			}
		
		}
	}
	@Override
	public String getName() {
		return "����";
	}
	
	@Override
	public void init() {
	}
	@Override
	public boolean isEnableUse() {
		return false;
	}
	
	Runnable ask = new Runnable() {
		
		@Override
		public void run() {
			Panel_HandCards ph = pc.getHand();
			ph.unableToUseCard();
			ph.enableOKAndCancel();
			
		}
	};
}

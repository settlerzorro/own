package sanguosha1.skills.active;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.skills.SkillIF;

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

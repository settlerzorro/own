package sanguosha1.skills.active;

import javax.swing.SwingUtilities;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.base.Card_Sha;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.skills.SkillIF;

/**
 * ������������
 * 
 * @author user
 * 
 */
public class LiuBei_JiJiang_Boss implements Runnable, SkillIF {
	AbstractPlayer player;
	Panel_Control pc;
	boolean isRequestUse;

	public LiuBei_JiJiang_Boss(AbstractPlayer player) {
		this.player = player;
	}

	@Override
	public void run() {
		pc = (Panel_Control) player.getPanel();
		// ��Ӧ��ס�����߳�
		if (player.getState().isRequest()) {
			//player.getProcess().setSkilling(true);
			isRequestUse = true;
		}
		SwingUtilities.invokeLater(ask);
		while (true) {
			if (player.getState().getRes() == Const_Game.OK) {
				// �����ͬ����
				for (AbstractPlayer p : player.getSameCountryPlayers()) {
					if (p.getRequest().requestSha()) {
						AbstractCard c = p.getState().getUsedCard().get(0);
						player.getState().getUsedCard().add(0, c);
						if (isRequestUse) {
							player.getState().setRes(Const_Game.SHA);
							try {
								//��ͣ1��ȷ���źű����ܵ�
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							new Card_Sha().executeSha(player, pc.getHand()
									.getTarget().getList().get(0));
						}
						break;
					}
				}
				//û�˳�ɱ
				if(isRequestUse){
					player.getState().setRes(Const_Game.REDO);
					return;
				}
				break;
			}
			if (player.getState().getRes() == Const_Game.CANCEL) {
				break;
			}
		}


		synchronized (player.getProcess()) {
			player.getState().setRes(0);
			player.refreshView();
			while(!player.getState().isRequest()){
				player.getProcess().notify();
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
		isRequestUse = false;
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
			ph.disableClick();
			ph.enableOKAndCancel();
			if(!isRequestUse){				
				new Card_Sha().targetCheck(ph);
			}
		}
	};
}

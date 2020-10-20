package sanguosha1.skills.active;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.gui.main.Panel_Player;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.SkillIF;

import javax.swing.*;
import java.util.List;
import java.util.Random;

/**
 * ��褡����䡿
 * 
 * @author user
 * 
 */
public class ZhouYu_fanjian implements Runnable, SkillIF {
	final Colors[] colors = {Colors.HEITAO,Colors.HONGXIN,Colors.MEIHUA,Colors.FANGKUAI};
	AbstractPlayer player;
	Panel_Control pc;
	Panel_HandCards ph;
	public ZhouYu_fanjian(AbstractPlayer p) {
		this.player = p;
	}

	@Override
	public void run() {
		 pc = (Panel_Control) player.getPanel();
		 ph = pc.getHand();
		SwingUtilities.invokeLater(run);
		while (true) {
			if (player.getState().getRes() == Const_Game.OK) {
				if(ph.getTarget().isEmpty()){
					player.getState().setRes(0);
					ph.enableOKAndCancel();
					System.out.println("��Ŀ�귵��");
					continue;
				}else{
					AbstractPlayer toP = ph.getTarget().getList().get(0);
					ViewManagement.getInstance().printBattleMsg(player.getInfo().getName()+"����"+toP.getInfo().getName());
					sleep(1000);
					//AIѡ��ɫ
					int indexAI = new Random().nextInt(4);
					Colors c_AI = colors[indexAI]; 
					ViewManagement.getInstance().printBattleMsg(toP.getInfo().getName()+"�»�ɫΪ��"+c_AI.toString());
					sleep(300);
					//AIѡ������
					int n = new Random().nextInt(player.getState().getCardList().size());
					AbstractCard card_AI =  player.getState().getCardList().get(n);
					//ViewManagement.getInstance().printBattleMsg(toP.getInfo().getName()+"ѡ����"+card_AI.toString());
					//����
					card_AI.passToPlayer(player, toP);
					sleep(300);
					//���ݻ�ɫ����
					if(c_AI != card_AI.getColor()){
						ViewManagement.getInstance().printBattleMsg(toP.getInfo().getName()+"�´�");
						toP.getAction().loseHP(1, null);
					}
					toP.refreshView();
					player.getState().setRes(0);
					break;
				}
			}
			if (player.getState().getRes() == Const_Game.CANCEL) {
				ViewManagement.getInstance().getPrompt().clear();
				player.getState().setRes(0);
				break;
			}
		}
		synchronized (player.getProcess()) {
			player.getProcess().notify();
			player.refreshView();
		}
	}

	@Override
	public String getName() {
		return "����";
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEnableUse() {
		// TODO Auto-generated method stub
		return false;
	}

	Runnable run = new Runnable() {

		@Override
		public void run() {
			
			ph.unableToUseCard();
			ph.disableClick();
			ph.enableOKAndCancel();
			// ����ѡ��
			List<Panel_Player> list = ph.getMain().getPlayers();
			for (Panel_Player pp : list) {
				if (!pp.getPlayer().getState().isDead()) {
					pp.enableToUse();
				}
			}
			ViewManagement.getInstance().getPrompt().show_Message("��ѡ��Ŀ��");
		}
	};
	
	/*
	 * ͣ�ټ��
	 */
	private void sleep(int t){
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

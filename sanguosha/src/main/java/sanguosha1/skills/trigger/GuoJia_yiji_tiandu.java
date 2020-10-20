package sanguosha1.skills.trigger;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Trigger;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;
import sanguosha1.skills.SkillMultiIF;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * ���Ρ��żơ�����ʡ�
 * 
 * @author user
 * 
 */
public class GuoJia_yiji_tiandu extends P_Trigger implements LockingSkillIF,SkillMultiIF {
	//��ʧ��õ�����
	private static final int NUMBER = 2;
	//AbstractPlayer player;
	//��¼����ȥ������
	int n ;
	//���UI��������
	Panel_Control pc;
	Panel_HandCards ph;

	//���ܼ���
	private static final String[] names = {"�ż�","���"};
	
	public GuoJia_yiji_tiandu(AbstractPlayer p) {
		this.player = p;
	}

	/**
	 * ��д���˴���
	 */
	@Override
	public void afterLoseHP(AbstractPlayer murderer) {
		super.afterLoseHP(murderer);
		System.out.println("��������ǰ�߳�Ϊ��" + Thread.currentThread().getName());
		// ���N����
		for (int i = 0; i < NUMBER; i++) {
			player.getAction().addOneCardFromList();
		}
		player.refreshView();
		//�����AI
		if(player.getState().isAI()){
			//TODO
			return;
		}
		pc = (Panel_Control) player.getPanel();
		ph = pc.getHand();
		//��ס
		player.getProcess().setSkilling(true);
		//��ʾ
		SwingUtilities.invokeLater(run);
		// �ȴ�����
		player.getState().setRes(0);
		n=0;
		while (true) {
			if (player.getState().getRes() == Const_Game.OK) {
				AbstractPlayer toP = ph.getTarget().getList().get(0);
				for (int i = 0; i < ph.getSelectedList().size(); i++) {
					ph.getSelectedList().get(i).getCard().passToPlayer(player,
							toP);
					n++;
				}
				toP.refreshView();
				// ��������ˣ�������ѭ��
				if (n >= NUMBER) {
					ViewManagement.getInstance().getPrompt().clear();
					break;
				} else {
					player.getState().setRes(0);
					player.refreshView();
					SwingUtilities.invokeLater(run);
				}
			}
			if (player.getState().getRes() == Const_Game.CANCEL) {
				ViewManagement.getInstance().getPrompt().clear();
				break;
			}
		}
		synchronized (player.getProcess()) {
			player.getState().setRes(0);
			player.getProcess().setSkilling(false);
			player.getProcess().notify();
		}
	}

	/**
	 * ��д�ж��󴥷�
	 * ��ȡ�ж���
	 */
	@Override
	public void afterCheck(AbstractCard c, boolean result) {
		ViewManagement.getInstance().printMsg(player.getInfo().getName()+"������ж��ƣ�"+c.toString());
		player.getAction().addCardToHandCard(c);
	}

	@Override
	public String getName() {
		return "�ż�";
	}
	
	private Runnable run = new Runnable() {

		@Override
		public void run() {
			// ��ʾ������
			pc.getHand().unableToUseCard();
			int size = pc.getHand().getCards().size();
			for (int i = 1; i <= NUMBER - n; i++) {
				pc.getHand().getCards().get(size - i).setEnableToUse(true);
				pc.getHand().setSelectLimit(NUMBER);
			}
			pc.getHand().enableToClick();
			ViewManagement.getInstance().getPrompt().show_Message("��ѡ��Ŀ�괫�ݣ�����ȡ��");
		}
	};

	@Override
	public List<String> getNameList() {
		return Arrays.asList(names);
	}

	
}

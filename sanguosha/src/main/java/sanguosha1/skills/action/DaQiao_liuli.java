package sanguosha1.skills.action;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.base.Card_Sha;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.gui.main.PaintService;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.gui.main.Panel_Player;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Action;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

import javax.swing.*;
import java.util.List;

/**
 * ���ǡ����롿
 * 
 * @author user
 * 
 */
public class DaQiao_liuli extends P_Action implements LockingSkillIF {
	// ��������
	AbstractCard cardThrow;
	// �����Ŀ��
	AbstractPlayer target;
	// ����
	AbstractPlayer pSha;
	Panel_Control pc;
	Panel_HandCards ph;

	public DaQiao_liuli(AbstractPlayer p) {
		super(p);
	}

	/**
	 * ��д�ر�ɱ���ж�
	 */
	@Override
	public boolean avoidSha(AbstractPlayer murder, Card_Sha card) {
		//���������˳�
		if(player.getState().getCardList().isEmpty())return false;
		pSha = murder;
		// ѯ���Ƿ�����
		// ѡ�� ȷ��
		if (player.getState().isAI()) {
			cardThrow = player.getState().getCardList().get(0);
			target = player.getNextPlayer();
			execute(murder, card);
			return true;
		} else {
			pc = (Panel_Control) player.getPanel();
			ph = pc.getHand();
			//SwingUtilities.invokeLater(ask);
			ViewManagement.getInstance().ask(player, getName());
			while (player.getState().getRes() == 0) {
				// ���ȷ�ϼ��ܷ���
				if (player.getState().getRes() == Const_Game.OK) {
					player.getProcess().setSkilling(true);
					player.getState().setRes(0);
					break;
				}
				if (player.getState().getRes() == Const_Game.CANCEL) {
					player.getState().setRes(0);
					return false;
				}
			}
			// ���뼼�� ѡ�����
			SwingUtilities.invokeLater(run);
			while (true) {
				if (player.getState().getRes() == Const_Game.OK) {
					if ((!ph.getSelectedList().isEmpty())
							&& !(ph.getTarget().isEmpty())) {
						cardThrow = ph.getSelectedList().get(0).getCard();
						target = ph.getTarget().getList().get(0);
						
						ViewManagement.getInstance().getPrompt().clear();
						execute(murder, card);
						player.getState().setRes(0);
						synchronized (player.getProcess()) {
							player.getProcess().notify();
						}
						return true;
					} else {
						ph.enableOKAndCancel();
						player.getState().setRes(0);
						continue;
					}
				}
				if (player.getState().getRes() == Const_Game.CANCEL) {
					player.getState().setRes(0);
					return false;
				}
			}
		}
		// return false;
	}

	private void execute(AbstractPlayer murder, Card_Sha csha) {
		cardThrow.throwIt(player);
		// ��Ϣ
		ViewManagement.getInstance().printBattleMsg(
				player.getInfo().getName() + "��"
						+ csha.toString() + "�����"
						+ target.getInfo().getName());
		// ���Ի���
		SwingUtilities.invokeLater(draw);
		csha.executeSha(murder, target);
	}

	@Override
	public String getName() {
		return "����";
	}


	Runnable run = new Runnable() {

		@Override
		public void run() {
			ViewManagement.getInstance().getPrompt().show_Message(
					"��ѡ�������ƣ���ѡ��Ŀ��");
			ph.remindToUseALL();
			ph.enableOKAndCancel();
			ph.getTarget().setLimit(1);
			ph.setTargetCheck(false);
			// ��������ڵ�����
			List<Panel_Player> list = ph.getMain().getPlayers();
			for (Panel_Player pp : list) {
				if (!pp.getPlayer().getState().isDead()
						&& player.getFunction().isInRange(pp.getPlayer())
						&& pp.getPlayer() != pSha) {
					pp.enableToUse();
				} else {
					pp.disableToUse();
				}
			}
		}
	};

	Runnable draw = new Runnable() {

		@Override
		public void run() {
			PaintService.drawLine(player, target);

		}
	};
}

package sgs.skills.action;

import sgs.card.AbstractCard;
import sgs.data.constant.Const_Game;
import sgs.gui.main.Panel_Control;
import sgs.gui.main.Panel_HandCards;
import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Action;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;

import javax.swing.*;

/**
 * ˾��ܲ�����š� �����ж���
 * 
 * @author user
 * 
 */
public class Simayi_guicai extends P_Action implements LockingSkillIF {
	Panel_Control pc;

	public Simayi_guicai(AbstractPlayer p) {
		super(p);
	}

	/**
	 * ��д�����ж���
	 */
	@Override
	public AbstractCard dealWithCheckCard(AbstractCard c) {
		if (!player.getState().getCardList().isEmpty()) {
			AbstractCard newCard = null;
			if (player.getState().isAI()) {
				newCard = player.getState().getCardList().get(0);
			} else {
				pc = (Panel_Control) player.getPanel();
				player.getProcess().setSkilling(true);
				SwingUtilities.invokeLater(select);
				while (true) {
					if (player.getState().getRes() == Const_Game.OK) {
						if(pc.getHand().getSelectedList().isEmpty()){
							continue;
						}
						newCard = pc.getHand().getSelectedList().get(0).getCard();
						ViewManagement.getInstance().getPrompt().clear();
						break;
					}
					if (player.getState().getRes() == Const_Game.CANCEL) {
						ViewManagement.getInstance().getPrompt().clear();
						synchronized (player.getProcess()) {
							player.refreshView();
							player.getProcess().setSkilling(false);
							player.getState().setRes(0);
							player.getProcess().notify();
						}
						return super.dealWithCheckCard(c);
					}
				}
				synchronized (player.getProcess()) {
					player.getProcess().setSkilling(false);
					player.getState().setRes(0);
					player.getProcess().notify();
				}
			}
			changeCard(c, newCard);
			return newCard;
		}
		return super.dealWithCheckCard(c);
	}

	private void changeCard(AbstractCard c, AbstractCard newCard) {
		ViewManagement.getInstance().printMsg(
				player.getInfo().getName() + "�����ж���" + newCard.toString());
		// ԭ�ȵ��ж����ӵ�
		c.gc();
		// ս����ʾ
		ModuleManagement.getInstance().getBattle().addOneCard(newCard);
		player.getAction().removeCard(newCard);
		player.refreshView();
	}

	@Override
	public String getName() {
		return "����";
	}

	Runnable select = new Runnable() {

		@Override
		public void run() {
			Panel_HandCards ph = pc.getHand();
			ViewManagement.getInstance().getPrompt().show_Message(
					"������ѡ��1�����滻�ж�������ȡ��");
			ph.remindToUseALL();
			ph.disableClick();
			ph.enableOKAndCancel();
			ph.setSelectLimit(1);
			ph.setTargetCheck(false);
		}
	};
}
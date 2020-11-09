package sanguosha1.skills.active;

import java.util.List;

import javax.swing.SwingUtilities;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.base.Card_Sha;
import sanguosha1.card.changed.Virtual_Sha;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.data.enums.ErrorMessageType;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.gui.main.Panel_Player;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.PlayerIF;
import sanguosha1.service.MessageManagement;
import sanguosha1.skills.ChangeCardIF;
import sanguosha1.skills.SkillIF;

/**
 * ���Թ����ܡ���ʥ��
 * 
 * @author user
 * 
 */
public class GuanYu_wusheng implements Runnable, SkillIF, ChangeCardIF {
	AbstractPlayer player;
	Panel_Control pc;

	public GuanYu_wusheng(AbstractPlayer player) {
		this.player = player;
	}

	@Override
	public void run() {
		pc = (Panel_Control) player.getPanel();
		// �������Ӧ
		if (player.getState().isRequest()) {
			useAsRequest();
			//unlock();
			return;
		}
		if (player.getState().isUsedSha()) {
			MessageManagement.printErroMsg(ErrorMessageType.hasUsed_Sha);
			unlock();
			return;
		}
		// ��ʾ���к�ɫ��
		SwingUtilities.invokeLater(showRedCards);
		// �ȴ�ѡ��
		while (true) {
			if (player.getState().getRes() == Const_Game.OK) {
				AbstractCard c = pc.getHand().getSelectedList().get(0)
						.getCard();
				// ԭ�е��ƶ���
				c.throwIt(player);
				// �³�һ������ɱ
				new Virtual_Sha(c).use(player, pc.getHand().getTarget()
						.getList().get(0));
				break;
			}
			if (player.getState().getRes() == Const_Game.CANCEL) {
				player.refreshView();
				break;
			}
		}
		unlock();
	}

	/*
	 * ��Ӧ�׶ε�ʹ��
	 */
	private void useAsRequest() {
		// ��ס��Ӧ
		player.getState().setRes(Const_Game.SKILL);
		// ��ʾ���к�ɫ��
		SwingUtilities.invokeLater(showRedCards);
		// �ȴ�ѡ��
		while (true) {
			if (player.getState().getRes() == Const_Game.OK) {
				/*AbstractCard c = pc.getHand().getSelectedList().get(0)
						.getCard();*/
				// ��������
				pc.getHand().updateCards();
				player.getState().setRes(getResultType());
				System.out.println("click ok!");
				break;
			}
			if (player.getState().getRes() == Const_Game.CANCEL) {
				player.getState().setRes(Const_Game.REDO);
				break;
			}
		}
		synchronized (player.getRequest()) {
			player.getRequest().notify();
		}
	}

	private void unlock() {
		// ����ڻغ��У��ͽ�غ���
		if (player.getStageNum() == PlayerIF.STAGE_USECARDS) {
			synchronized (player.getProcess()) {
				player.getState().setRes(0);
				player.getProcess().notify();
			}
		}
	}

	@Override
	public String getName() {
		return "��ʥ";
	}

	@Override
	public boolean isEnableUse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getResultType() {
		return Const_Game.SHA;
	}

	Runnable showRedCards = new Runnable() {

		@Override
		public void run() {

			Panel_HandCards ph = pc.getHand();
			ph.unableToUseCard();
			ph.remindToUse(Colors.HONGXIN, Colors.FANGKUAI);
			ph.setSelectLimit(1);
			ph.disableClick();
			ph.enableOKAndCancel();
			ph.setTargetCheck(false);
			if (player.getState().isRequest()) {
				return;
			}
			// ���� ���
			List<Panel_Player> list = pc.getMain().getPlayers();
			for (Panel_Player pp : list) {
				if (new Card_Sha().isInRange(player, pp.getPlayer())) {
					pp.enableToUse();
				} else {
					pp.disableToUse();
				}
			}

		}
	};
}

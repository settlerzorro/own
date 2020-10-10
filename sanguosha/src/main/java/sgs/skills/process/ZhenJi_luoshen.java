package sgs.skills.process;

import sgs.card.AbstractCard;
import sgs.data.constant.Const_Game;
import sgs.data.enums.Colors;
import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Process;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;

/**
 * �缧���ܡ�����
 * 
 * @author user
 * 
 */
public class ZhenJi_luoshen extends P_Process implements LockingSkillIF {

	public ZhenJi_luoshen(AbstractPlayer p) {
		super(p);
	}

	/**
	 * ��д�غϿ�ʼ
	 */
	@Override
	public void stage_begin() {
		if (!player.getState().isAI()) {
			ViewManagement.getInstance().ask(player, getName());
			while (true) {
				if (player.getState().getRes() == Const_Game.OK) {
					ViewManagement.getInstance().printBattleMsg(
							player.getInfo().getName() + "����" + getName());
					ViewManagement.getInstance().getPrompt().clear();
					player.getState().setRes(0);
					break;
				}
				if (player.getState().getRes() == Const_Game.CANCEL) {
					player.getState().setRes(0);
					return;
				}
			}
		}
		super.stage_begin();
		// ���к�ɫ�ж�
		while (true) {
			sleep(1000);
			AbstractCard cc = ModuleManagement.getInstance().showOneCheckCard();
			if (player.getFunction().checkRollCard(cc, Colors.HEITAO,
					Colors.MEIHUA)) {
				player.getAction().addCardToHandCard(cc);
			} else {
				sleep(1000);
				break;
			}
			sleep(1000);
		}
	}

	@Override
	public String getName() {
		return "����";
	}

	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

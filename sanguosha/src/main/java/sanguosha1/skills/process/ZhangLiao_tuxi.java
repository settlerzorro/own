package sanguosha1.skills.process;

import sanguosha1.data.constant.Const_Game;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_Player;
import sanguosha1.gui.main.Panel_SelectCard;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.impl.P_Process;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.LockingSkillIF;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * ���ɡ�ͻϮ��
 * 
 * @author user
 * 
 */
public class ZhangLiao_tuxi extends P_Process implements LockingSkillIF {
	Panel_Control pc;

	public ZhangLiao_tuxi(AbstractPlayer p) {
		super(p);
	}

	/**
	 * ��д���ƽ׶�
	 */
	@Override
	public void stage_addCards() {
		if(player.getState().isAI())return;
		//ѯ���Ƿ񷢶�����
		SwingUtilities.invokeLater(ask);
		pc = (Panel_Control) player.getPanel();
		while(true){
			if(player.getState().getRes() == Const_Game.OK){
				//player.getState().setRes(0);
				break;
			}
			if(player.getState().getRes() == Const_Game.CANCEL){
				super.stage_addCards();
				return;
			}
		}
		//�������ѡ��
		SwingUtilities.invokeLater(run);
		while(true){
			if(player.getState().getRes() == Const_Game.OK &!pc.getHand().getTarget().isEmpty()){
				player.getState().setRes(0);
				pc.getHand().enableOKAndCancel();
				break;
			}
			if(player.getState().getRes() == Const_Game.CANCEL){
				super.stage_addCards();
				return;
			}
		}
		
		while (true) {
			if (player.getState().getRes() == Const_Game.OK) {
				ViewManagement.getInstance().printBattleMsg(
						player.getInfo().getName() + "����" + getName());
				ViewManagement.getInstance().getPrompt().clear();
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						@Override
						public void run() {
							System.out.println("in");
							AbstractPlayer toP = pc.getHand().getTarget().getList()
									.get(0);
							Panel_SelectCard ps = new Panel_SelectCard(player, toP,
									Panel_SelectCard.SHUN, Panel_SelectCard.ONLY_HAND);
							pc.getMain().add(ps, 0);
							pc.getHand().unableToUseCard();
							pc.getHand().disableClick();
							ps.setLocation(200, pc.getMain().getHeight() / 9);
							pc.getMain().validate();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				player.getState().setRes(0);
				break;
			}
			if (player.getState().getRes() == Const_Game.CANCEL) {
				player.getState().setRes(0);
				super.stage_addCards();
				return;
			}
		}
	}

	@Override
	public String getName() {
		return "ͻϮ";
	}

	Runnable run = new Runnable() {
		@Override
		public void run() {
			pc.getHand().unableToUseCard();
			pc.getHand().disableClick();
			// �������ѡ��
			List<Panel_Player> list = pc.getHand().getMain().getPlayers();
			for (Panel_Player pp : list) {
				if (!pp.getPlayer().getState().isDead()) {
					pp.enableToUse();
				}
			}
			ViewManagement.getInstance().getPrompt().show_Message("��ѡ��Ŀ��");
		}
	};
	
	Runnable ask = new Runnable() {
		@Override
		public void run() {
			ViewManagement.getInstance().getPrompt().show_Message("�Ƿ񷢶�"+getName());
			pc.getHand().unableToUseCard();
			pc.getHand().enableOKAndCancel();
		}
	};
}

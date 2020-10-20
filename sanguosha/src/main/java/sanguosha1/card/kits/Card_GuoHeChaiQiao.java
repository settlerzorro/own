package sanguosha1.card.kits;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.EffectCardIF;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.gui.main.Panel_Player;
import sanguosha1.gui.main.Panel_SelectCard;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * ���Ӳ���
 * 
 * @author user
 * 
 */
public class Card_GuoHeChaiQiao extends AbstractKitCard implements EffectCardIF{

	Panel_SelectCard ps;

	public Card_GuoHeChaiQiao() {

	}

	/**
	 * ��дuse ��Ŀ����ҵ���������ѡ��һ�� ɾ��
	 */
	@Override
	public void use(final AbstractPlayer p, List<AbstractPlayer> players) {
		super.use(p, players);
		// ��������
		p.getTrigger().afterMagic();

		// �����и����return
		askWuXieKeJi(p, players);
		if (isWuXie) {
			ViewManagement.getInstance().printBattleMsg(getName() + "��Ч");
			ViewManagement.getInstance().refreshAll();
			return;
		}
		if (p.getState().isAI()) {
			AbstractPlayer target = players.get(0);
			if(!target.getState().getCardList().isEmpty()){
				AbstractCard c = target.getState().getCardList().get(0);
				target.getAction().removeCard(c);
				c.gc();
				ModuleManagement.getInstance().getBattle().addOneCard(c);
				p.refreshView();
				target.refreshView();
			}
		
		} else {
			// pc = (Panel_Control)p.getPanel();
			ps = new Panel_SelectCard(p, targetPlayers.get(0),
					Panel_SelectCard.CHAI);
			// ��ʾѡ�����ȴ�����
			try {
				SwingUtilities.invokeAndWait(run);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			// p.getTrigger().afterMagic();
			// p.refreshView();
		}
	}
	/**
	 * ��дĿ����
	 */
	@Override
	public void targetCheck(Panel_HandCards ph) {
		// ���� ���
		List<Panel_Player> list = ph.getMain().getPlayers();
		for (int i = 0; i < list.size(); i++) {
			Panel_Player pp = list.get(i);
			// ��������ƻ���װ����
			if (pp.getPlayer().getState().getCardList().isEmpty()
					&& pp.getPlayer().getState().getEquipment().isEmpty()) {
				pp.disableToUse();
				continue;
			}
			pp.enableToUse();
		}
	}
	
	
	Runnable run = new Runnable() {

		@Override
		public void run() {
			pc.getPlayer().refreshView();
			pc.getMain().add(ps, 0);
			pc.getHand().unableToUseCard();
			ps.setLocation(200, pc.getMain().getHeight() / 9);
			pc.getMain().validate();
		}
	};
}

package sanguosha1.card.equipment;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_Main;
import sanguosha1.gui.main.Panel_SelectCard;
import sanguosha1.player.AbstractPlayer;

/**
 * 麒麟弓
 * 
 * @author user
 * 
 */
public class Card_QiLinGong extends AbstractWeaponCard {
	Panel_Control pc;
	public Card_QiLinGong(){
		super();
	}
	public Card_QiLinGong(int id, int number, Colors color) {
		super(id, number, color);
	}

	/**
	 * 重写 杀造成伤害后的触发
	 */
	@Override
	public void damageTrigger(final AbstractPlayer p,
			final AbstractPlayer target) {
		super.damageTrigger(p, target);
		// 如果有码
		if (target.getState().getEquipment().hasHorse()) {
			if (p.getState().isAI()) {
				return;
			} else {
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						@Override
						public void run() {
							
							pc = (Panel_Control) p.getPanel();
							Panel_Main pm = pc.getMain();
							Panel_SelectCard ps = new Panel_SelectCard(p,
									target, Panel_SelectCard.CHAI, Panel_SelectCard.ONLY_EQ);
							ps.setLocation(200, pc.getMain().getHeight() / 9);
							pm.add(ps, 0);
							pc.getHand().unableToUseCard();
							//pc.getHand().disableClick();
							pm.validate();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				//锁住
				synchronized (p.getProcess()) {
					try {
						p.getProcess().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}

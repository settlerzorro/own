package sanguosha1.card.base;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.EffectCardIF;
import sanguosha1.gui.main.PaintService;
import sanguosha1.player.AbstractPlayer;

import javax.swing.*;
import java.util.List;

/**
 * �������Ƶ���
 * 
 * @author user
 * 
 */
public class Card_Shan extends AbstractCard implements EffectCardIF {
	public Card_Shan() {

	}

	/**
	 * ��Ӧʹ��
	 */
	@Override
	public boolean requestUse(AbstractPlayer p, List<AbstractPlayer> players) {
		boolean flag = super.requestUse(p, players);
		drawEffect(p, players);
		return flag;
	}

	/**
	 * ��д����Ч��
	 */
	@Override
	protected void drawEffect(final AbstractPlayer p,
			List<AbstractPlayer> players) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				PaintService.drawEffectImage(getEffectImage(), p);
				PaintService.clearAfter(1000);
			}
		});
	}
}

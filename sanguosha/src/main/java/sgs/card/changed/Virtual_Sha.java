package sgs.card.changed;

import sgs.card.AbstractCard;
import sgs.card.VirtualCardIF;
import sgs.card.base.Card_Sha;
import sgs.gui.main.PaintService;
import sgs.player.AbstractPlayer;
import sgs.service.ViewManagement;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * ����ġ�ɱ��
 * @author user
 *
 */
public class Virtual_Sha implements VirtualCardIF{
	AbstractCard realCard;
	public Virtual_Sha(AbstractCard realCard){
		this.realCard = realCard;
	}
	
	/**
	 * �����Ƶ�ʹ��
	 */
	public void use(final AbstractPlayer p,final AbstractPlayer toP){
		final Card_Sha cs = new Card_Sha();
		// ����ɱ
		ViewManagement.getInstance().printBattleMsg(
				p.getInfo().getName() + "��" + toP.getInfo().getName()
				+ "ʹ����ɱ" );
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					p.refreshView();
					PaintService.drawEffectImage(cs.getEffectImage(),p);
					PaintService.drawLine(p,toP);
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		cs.executeSha(p, toP);
	}
	
	
	@Override
	public int getCardType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractCard getRealCard() {
		return realCard;
	}

}

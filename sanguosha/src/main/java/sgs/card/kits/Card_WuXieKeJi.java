package sgs.card.kits;

import sgs.card.EffectCardIF;
import sgs.gui.main.PaintService;
import sgs.player.AbstractPlayer;

import javax.swing.*;
import java.util.List;

/**
 * ��и�ɻ�
 * @author user
 *
 */
public class Card_WuXieKeJi extends AbstractKitCard implements EffectCardIF{
	public Card_WuXieKeJi(){}
	
	/**
	 * �������
	 */
	@Override
	public void use(AbstractPlayer p, List<AbstractPlayer> players) {
		super.requestUse(p, players);
	}

	/**
	 * ��Ӧ���
	 */
	@Override
	public boolean requestUse(final AbstractPlayer p, List<AbstractPlayer> players) {
		super.requestUse(p, players);
		drawRequestEffect(p);
		//������˳���и ��this.iswuxie == true ��ô������иӦ�÷���false;
		askWuXieKeJi(p, players);
		return !isWuXie;
	}
	
	/*
	 * ��Ӧ����ʱ��Ļ���
	 */
	private void drawRequestEffect(final AbstractPlayer p){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(getEffectImage()!=null)PaintService.drawEffectImage(getEffectImage(), p);
				PaintService.clearAfter(1000);
			}
		});
	}
}

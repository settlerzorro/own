package sgs.card.kits;

import sgs.card.EffectCardIF;
import sgs.gui.main.PaintService;
import sgs.player.AbstractPlayer;

import javax.swing.*;
import java.util.List;

/**
 * 无懈可击
 * @author user
 *
 */
public class Card_WuXieKeJi extends AbstractKitCard implements EffectCardIF{
	public Card_WuXieKeJi(){}
	
	/**
	 * 主动打出
	 */
	@Override
	public void use(AbstractPlayer p, List<AbstractPlayer> players) {
		super.requestUse(p, players);
	}

	/**
	 * 响应打出
	 */
	@Override
	public boolean requestUse(final AbstractPlayer p, List<AbstractPlayer> players) {
		super.requestUse(p, players);
		drawRequestEffect(p);
		//如果有人出无懈 则this.iswuxie == true 那么本次无懈应该返回false;
		askWuXieKeJi(p, players);
		return !isWuXie;
	}
	
	/*
	 * 响应出牌时候的绘制
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

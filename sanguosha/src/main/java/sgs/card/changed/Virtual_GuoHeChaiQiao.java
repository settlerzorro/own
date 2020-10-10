package sgs.card.changed;

import sgs.card.AbstractCard;
import sgs.card.EffectCardIF;
import sgs.card.VirtualCardIF;
import sgs.card.kits.Card_GuoHeChaiQiao;
import sgs.data.constant.Const_Game;
import sgs.gui.main.PaintService;
import sgs.gui.main.Panel_Control;
import sgs.gui.main.Panel_SelectCard;
import sgs.player.AbstractPlayer;
import sgs.service.ViewManagement;
import sgs.util.ImgUtil;

import javax.swing.*;

/**
 * 虚拟的过河拆桥
 * @author user
 *
 */
public class Virtual_GuoHeChaiQiao extends Card_GuoHeChaiQiao implements VirtualCardIF,EffectCardIF{
	AbstractCard realCard;
	
	public Virtual_GuoHeChaiQiao(AbstractCard real){
		this.realCard = real;
		this.name = "过河拆桥";
		this.effectImage = ImgUtil.getPngImgByName("ef_guohechaiqiao");
	}
	
	@Override
	public int getCardType() {
		return Const_Game.GUOHECHAIQIAO;
	}

	@Override
	public AbstractCard getRealCard() {
		return realCard;
	}

	//使用
	@Override
	public void use(final AbstractPlayer p, final AbstractPlayer toP) {
		System.out.println("过河拆桥线程："+Thread.currentThread().getName());
		//绘制
		drawEffect(p, toP);
		// 如果无懈，则return
		askWuXieKeJi(p, null );
		if (isWuXie) {
			ViewManagement.getInstance().printBattleMsg(getName() + "无效");
			ViewManagement.getInstance().refreshAll();
			return;
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Panel_Control pc = (Panel_Control)p.getPanel();
				Panel_SelectCard ps = new Panel_SelectCard(p, toP,Panel_SelectCard.CHAI);
				pc.getMain().add(ps,0);
				pc.getHand().unableToUseCard();
				ps.setLocation(200,pc.getMain().getHeight()/9);
				pc.getMain().validate();
			}
		});
	
	}

	//绘制
	protected void drawEffect(final AbstractPlayer p, final AbstractPlayer toP) {
		ViewManagement.getInstance().printBattleMsg(
				p.getInfo().getName() + "对" + toP.getInfo().getName()
						+ "使用了"+getName());
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (getEffectImage() != null)
					PaintService.drawEffectImage(getEffectImage(), p);
					PaintService.drawLine(p, toP);
			}
		});
	}
}

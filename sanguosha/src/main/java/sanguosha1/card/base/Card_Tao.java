package sanguosha1.card.base;

import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.SwingUtilities;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.CardIF;
import sanguosha1.card.EffectCardIF;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.PaintService;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ViewManagement;
import sanguosha1.util.ImgUtil;

/**
 * Ã“
 * @author user
 *
 */
public class Card_Tao extends AbstractCard implements EffectCardIF{
	public Card_Tao(){
		
	}
	public Card_Tao(int id, int number, Colors color) {
		
		super(id, number, color);
		this.name = "Ã“";
		this.type = Const_Game.TAO;
		this.targetType = CardIF.NONE;
	}

	public Image showImg() {
		return ImgUtil.getPngImgByName("ktao");
	}

	@Override
	public void use(AbstractPlayer p, List<AbstractPlayer> players) {
		super.use(p, players);
		// TODO Auto-generated method stub
		ViewManagement.getInstance().printBattleMsg(p.getInfo().getName()+" π”√¡ÀÃ“");
		p.getAction().tao();
		p.getPanel().refresh();
	}

	
	
	@Override
	protected void drawEffect(final AbstractPlayer p, List<AbstractPlayer> players) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					PaintService.drawEffectImage(getEffectImage(), p);
					PaintService.clearAfter(1000);
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Image getEffectImage() {
		return ImgUtil.getPngImgByName("ef_tao");
	}
}

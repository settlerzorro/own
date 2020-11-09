package sanguosha1.card.kits;

import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.SwingUtilities;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.base.Card_Sha;
import sanguosha1.card.equipment.AbstractEquipmentCard;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.PaintService;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.gui.main.Panel_SelectCard;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;
import sanguosha1.util.ImgUtil;

/**
 * 借刀杀人
 * 
 * @author user
 * 
 */
public class Card_JieDaoShaRen extends AbstractKitCard {
	Panel_Control pc;
	Panel_SelectCard ps;
	public Card_JieDaoShaRen(){}
	public Card_JieDaoShaRen(int id, int number, Colors color) {
		super(id, number, color);
		this.name = "借刀杀人";
		this.type = Const_Game.JIEDAOSHAREN;
		this.targetType = SELECT;
	}

	@Override
	public Image showImg() {
		return ImgUtil.getPngImgByName("J_jiedaosharen");
	}

	/**
	 * 重写目标检测
	 */
	@Override
	public void targetCheck(Panel_HandCards ph) {
		ph.getTarget().setLimit(2);

	}

	/**
	 * 重写use
	 */
	@Override
	public void use(final AbstractPlayer p, List<AbstractPlayer> players) {
		if (players.size() < 2
				|| !players.get(0).getState().getEquipment().hasWeapons()) {
			p.refreshView();
			return;
		}
		super.use(p, players);
		final AbstractPlayer p1 = players.get(0);
		final AbstractPlayer p2 = players.get(1);
		
		//玩家的响应
		AbstractCard res ;
		//AI的选择
		if(p1.getState().isAI()){
			res = null;
		}else{
			//玩家的选择
			//暂时没有
			res = null;
		}
		//根据res来操作
		if(res!=null){
			Card_Sha c = (Card_Sha) res;
			c.executeSha(p1, p2);
		}else{
			AbstractEquipmentCard c = p1.getState().getEquipment().getWeapons();
			c.unload(p1);
			ModuleManagement.getInstance().getGcList().remove(c);
			p.getAction().addCardToHandCard(c);
			p.refreshView();
			p1.refreshView();
			p2.refreshView();
		}
	}

	@Override
	public Image getEffectImage() {
		return ImgUtil.getPngImgByName("ef_jiedaosharen");
	}

	@Override
	protected void drawEffect(final AbstractPlayer p, List<AbstractPlayer> players) {
		//绘制和信息
		final AbstractPlayer p1 = players.get(0);
		final AbstractPlayer p2 = players.get(1);
		ViewManagement.getInstance().printBattleMsg(
				p.getInfo().getName() + "对"
				+ p1.getInfo().getName() + "使用"
				+ getName());
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					p.refreshView();
					PaintService.drawLine(p, p1);
					PaintService.drawLine(p1, p2);
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}}

	
}

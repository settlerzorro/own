package sgs.card.kits;

import sgs.card.AbstractCard;
import sgs.card.base.Card_Sha;
import sgs.card.equipment.AbstractEquipmentCard;
import sgs.data.constant.Const_Game;
import sgs.data.enums.Colors;
import sgs.gui.main.PaintService;
import sgs.gui.main.Panel_Control;
import sgs.gui.main.Panel_HandCards;
import sgs.gui.main.Panel_SelectCard;
import sgs.player.AbstractPlayer;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;
import sgs.util.ImgUtil;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

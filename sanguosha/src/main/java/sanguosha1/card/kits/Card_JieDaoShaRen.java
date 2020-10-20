package sanguosha1.card.kits;

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

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * �赶ɱ��
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
		this.name = "�赶ɱ��";
		this.type = Const_Game.JIEDAOSHAREN;
		this.targetType = SELECT;
	}

	@Override
	public Image showImg() {
		return ImgUtil.getPngImgByName("J_jiedaosharen");
	}

	/**
	 * ��дĿ����
	 */
	@Override
	public void targetCheck(Panel_HandCards ph) {
		ph.getTarget().setLimit(2);

	}

	/**
	 * ��дuse
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
		
		//��ҵ���Ӧ
		AbstractCard res ;
		//AI��ѡ��
		if(p1.getState().isAI()){
			res = null;
		}else{
			//��ҵ�ѡ��
			//��ʱû��
			res = null;
		}
		//����res������
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
		//���ƺ���Ϣ
		final AbstractPlayer p1 = players.get(0);
		final AbstractPlayer p2 = players.get(1);
		ViewManagement.getInstance().printBattleMsg(
				p.getInfo().getName() + "��"
				+ p1.getInfo().getName() + "ʹ��"
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

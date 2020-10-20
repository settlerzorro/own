package sanguosha1.card.changed;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.DelayKitIF;
import sanguosha1.card.VirtualCardIF;
import sanguosha1.card.kits.Card_LeBuSiShu;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.PaintService;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * ����ġ��ֲ�˼��
 * @author user
 *
 */
public class Virtual_LeBuSiShu extends Card_LeBuSiShu implements VirtualCardIF,DelayKitIF{
	AbstractCard realCard;
	//Card_LeBuSiShu card_le = new Card_LeBuSiShu();
	AbstractPlayer owner ;
	public Virtual_LeBuSiShu(AbstractCard real){
		this.realCard = real;
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

	@Override
	public void use(final AbstractPlayer p, final AbstractPlayer toP) {
		//final AbstractPlayer target = players.get(0);
		owner = toP;
		//��ʾ��Ϣ�����Ƶ�Ч��
		ViewManagement.getInstance().printBattleMsg(p.getInfo().getName()+"��"+toP.getInfo().getName()+"ʹ���ˡ���ɫ��");
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					PaintService.drawLine(p, toP);
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//�ƶ��ջ�
		ModuleManagement.getInstance().getGcList().remove(this);
		//Ŀ���ж������
		toP.getState().getCheckedCardList().add(this);
		p.refreshView();
		toP.refreshView();
	}

	@Override
	public void doKit() {
		AbstractCard cc = ModuleManagement.getInstance().showOneCheckCard();
		boolean flag = owner.getFunction().checkRollCard(cc, Colors.HONGXIN);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(flag){			
			ViewManagement.getInstance().printBattleMsg(getName()+"ʧЧ");
		}else{
			ViewManagement.getInstance().printBattleMsg(getName()+"��Ч");
		}
		owner.getProcess().setCanUseCard(flag);
		owner.getState().getCheckedCardList().remove(this);
	}

	@Override
	public int getKitCardType() {
		return Const_Game.LEBUSISHU;
	}

	@Override
	public String getShowNameInPanel() {
		return "��";
	}

	@Override
	public Image showImg() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getName(){
		return "�ֲ�˼��";
	}
}

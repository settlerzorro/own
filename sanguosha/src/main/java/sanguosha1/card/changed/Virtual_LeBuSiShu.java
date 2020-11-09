package sanguosha1.card.changed;

import java.awt.Image;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

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

/**
 * 虚拟的【乐不思蜀】
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
		//显示信息及绘制等效果
		ViewManagement.getInstance().printBattleMsg(p.getInfo().getName()+"对"+toP.getInfo().getName()+"使用了【国色】");
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
		//牌堆收回
		ModuleManagement.getInstance().getGcList().remove(this);
		//目标判定区添加
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
			ViewManagement.getInstance().printBattleMsg(getName()+"失效");
		}else{
			ViewManagement.getInstance().printBattleMsg(getName()+"生效");
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
		return "乐";
	}

	@Override
	public Image showImg() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getName(){
		return "乐不思蜀";
	}
}

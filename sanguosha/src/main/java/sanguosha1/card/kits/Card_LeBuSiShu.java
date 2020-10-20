package sanguosha1.card.kits;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.DelayKitIF;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.gui.main.Panel_Player;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;

import java.util.List;

/**
 * �ֲ�˼��
 * @author user
 *
 */
public class Card_LeBuSiShu extends AbstractKitCard implements DelayKitIF{
	AbstractPlayer owner;
	public Card_LeBuSiShu(){
	}

	/**
	 * ��дuse
	 */
	@Override
	public void use(final AbstractPlayer p, List<AbstractPlayer> players) {
		super.use(p, players);
		final AbstractPlayer target = players.get(0);
		owner = target;

		//�ƶ��ջ�
		ModuleManagement.getInstance().getGcList().remove(this);
		//Ŀ���ж������
		target.getState().getCheckedCardList().add(this);
		p.refreshView();
		target.refreshView();
	}

	/**
	 * ���ҷ���
	 */
	@Override
	public void doKit() {
		//��и
		askWuXieKeJi(owner, null);
		if (isWuXie) {
			ViewManagement.getInstance().printBattleMsg(getName() + "��Ч");
			ViewManagement.getInstance().refreshAll();
			owner.getProcess().setCanUseCard(true);
			owner.getState().getCheckedCardList().remove(this);
			gc();
			return;
		}
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
		gc();
	}

	@Override
	public String getShowNameInPanel() {
		return "��";
	}
	
	@Override
	public int getKitCardType() {
		return type;
	}

	@Override
	public void targetCheck(Panel_HandCards ph) {
		List<Panel_Player> list = ph.getMain().getPlayers();
		for (Panel_Player pp : list) {
			if (!pp.getPlayer().getState().isDead()) {
				if(pp.getPlayer().getState().hasDelayKit(Const_Game.LEBUSISHU)){
					pp.disableToUse();
				}
			}
		}
	}
	public AbstractPlayer getOwner() {
		return owner;
	}
	public void setOwner(AbstractPlayer owner) {
		this.owner = owner;
	}
	
}

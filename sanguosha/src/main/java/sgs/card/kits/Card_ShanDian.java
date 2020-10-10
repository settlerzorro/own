package sgs.card.kits;

import sgs.card.AbstractCard;
import sgs.card.DelayKitIF;
import sgs.data.enums.Colors;
import sgs.player.AbstractPlayer;
import sgs.service.ModuleManagement;
import sgs.service.ViewManagement;

import java.util.List;

/**
 * ����
 * 
 * @author user
 * 
 */
public class Card_ShanDian extends AbstractKitCard implements DelayKitIF {
	// �˺�
	final int DAMAGE = 3;
	// ��ǰӵ����
	AbstractPlayer owner;

	public Card_ShanDian() {

	}

	/**
	 * ��дuse
	 */
	@Override
	public void use(final AbstractPlayer p, List<AbstractPlayer> players) {
		super.use(p, players);
		owner = p;
		// �ƶ��ջ�
		ModuleManagement.getInstance().getGcList().remove(this);
		// ��������Ѿ������磬�����¼Ҵ�
		if (p.getState().hasDelayKit(type)) {
			pass();
			return;
		}
		// Ŀ���ж������
		p.getState().getCheckedCardList().add(this);
		p.refreshView();
	}

	/**
	 * ���ܷ���
	 */
	@Override
	public void doKit() {
		askWuXieKeJi(owner, null);
		if (isWuXie) {
			ViewManagement.getInstance().printBattleMsg(getName() + "��Ч");
			ViewManagement.getInstance().refreshAll();
			pass();
			return;
		}
		AbstractCard cc = ModuleManagement.getInstance().showOneCheckCard();
		boolean flag = owner.getFunction().checkRollCard(cc, Colors.HEITAO)
				&& owner.getFunction().checkRollCard(cc, 9, 2);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (flag) {
			ViewManagement.getInstance().printBattleMsg(getName() + "��Ч");
			owner.getAction().loseHP(DAMAGE, null);
			owner.getState().getCheckedCardList().remove(this);
			gc();
		} else {
			ViewManagement.getInstance().printBattleMsg(getName() + "ʧЧ");
			pass();
		}
	}

	/*
	 * ����
	 */
	private void pass() {
		owner.getState().getCheckedCardList().remove(this);
		AbstractPlayer next = owner.getNextPlayer();
		// ���¼�������������¼�
		if (next.getState().hasDelayKit(this.type))
			next = next.getNextPlayer();
		next.getState().getCheckedCardList().add(this);
		owner.refreshView();
		next.refreshView();
		owner = next;
	}

	@Override
	public String getShowNameInPanel() {
		return "��";
	}

	@Override
	public int getKitCardType() {
		return type;
	}
}

package sanguosha1.card.equipment;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.WeaponCardIF;
import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;

/**
 * ����������
 * 
 * @author user
 * 
 */
public abstract class AbstractWeaponCard extends AbstractEquipmentCard
		implements WeaponCardIF {

	public AbstractWeaponCard() {
		super();
	}

	public AbstractWeaponCard(int id, int number, Colors color) {
		super(id, number, color);
	}

	/**
	 * ��װ����ɱ ����һϵ������ ����������������д���еĲ��ֵ����� ��ʵ���似����Ч
	 */
	@Override
	public void shaWithEquipment(AbstractPlayer p, AbstractPlayer target,
			AbstractCard card) {
		// ɱ����֮ǰ�Ĵ�������
		useSkillBeforeSha(p, target);
		// ����Է��з������ж�Ϊ��Ч�򷵻�
		if (checkArmor(p, target)) {
			falseTrigger(p, target);
			return;
		}
		// ����˺����߱��������ö�Ӧ�Ĵ����¼�
		if (callSha(p, target)) {
			damageTrigger(p, target);
		} else {
			System.out.println("chufa");
			falseTrigger(p, target);
		}
		// �������Ĵ����¼�
		afterSha(p, target);
		
	}

	@Override
	public void afterSha(AbstractPlayer p, AbstractPlayer target) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean callSha(AbstractPlayer p, AbstractPlayer target) {
		return p.getAction().sha(target);
	}

	/**
	 * �����ж�
	 */
	@Override
	public boolean checkArmor(AbstractPlayer p, AbstractPlayer target) {
		// �ж�����
		ArmorIF am = (ArmorIF) target.getState().getEquipment().getArmor();
		if (am==null || !am.check(this,target)) {
			return false;
		}
		return true;
	}

	@Override
	public void damageTrigger(AbstractPlayer p, AbstractPlayer target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void falseTrigger(AbstractPlayer p, AbstractPlayer target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void useSkillBeforeSha(AbstractPlayer p, AbstractPlayer target) {
		// TODO Auto-generated method stub

	}
}

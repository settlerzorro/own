package sanguosha1.card;

import sanguosha1.player.AbstractPlayer;

/**
 * �����ӿ�
 * ӵ��һ����������ɱ�ķ���
 * @author user
 *
 */
public interface WeaponCardIF {
	//��������ɱ���������¼�������
	void shaWithEquipment(AbstractPlayer p, AbstractPlayer target, AbstractCard card);

	//ɱǰ�ļ���
	void useSkillBeforeSha(AbstractPlayer p, AbstractPlayer target);
	//�Է������ж�
	boolean checkArmor(AbstractPlayer p, AbstractPlayer target);
	//���������ɱ
	boolean callSha(AbstractPlayer p, AbstractPlayer target);
	//���ɱ�ɹ�����
	void damageTrigger(AbstractPlayer p, AbstractPlayer target);
	//�����������
	void falseTrigger(AbstractPlayer p, AbstractPlayer target);
	//ɱ���Ĵ���
	void afterSha(AbstractPlayer p, AbstractPlayer target);
	
}
